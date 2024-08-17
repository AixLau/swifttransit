package com.swifttranist.gateway.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.swifttranist.gateway.constant.GatewayConstant;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 动态路由服务实现类
 * 该类通过实现 ApplicationEventPublisherAware 接口来发布路由刷新事件，
 * 使 Spring Cloud Gateway 能够动态地从 Nacos 配置中心加载路由信息。
 */
@Service
@Slf4j
@RequiredArgsConstructor
@DependsOn("gatewayConstant")
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    /**
     * 删除和保存路由
     */
    private final RouteDefinitionWriter routeDefinitionWriter;

    /**
     * 获取当前已加载路由
     */
    private final RouteDefinitionLocator routeDefinitionLocator;

    /**
     * 从 Nacos 获取配置
     */
    private final NacosConfigManager nacosConfigManager;

    /**
     * 发布事件
     */
    private ApplicationEventPublisher publisher;

    /**
     * 设置事件发布器
     * 通过实现 ApplicationEventPublisherAware 接口，
     * 该方法将被 Spring 在启动时调用，以注入事件发布器。
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @PostConstruct
    public void init() {
        // 加载初始路由
        updateRoutesFromNacos(getRoutesConfigFromNacos());
        // 添加 Nacos 配置监听器
        try {
            nacosConfigManager.getConfigService().addListener(
                    GatewayConstant.NACOS_ROUTE_DATA_ID,
                    GatewayConstant.NACOS_ROUTER_GROUP,
                    new Listener() {
                        @Override
                        public Executor getExecutor() {
                            return null; // 使用 Nacos 默认的 Executor
                        }

                        @Override
                        public void receiveConfigInfo(String config) {
                            log.info("Nacos 配置发生变化，更新路由...");
                            updateRoutesFromNacos(config);
                        }
                    }
            );
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从 Nacos 中获取最新路由配置，并根据已有路由进行更新或删除操作
     *
     * @param routesConfig Nacos 配置返回的 JSON 字符串
     */
    private void updateRoutesFromNacos(String routesConfig) {
        // 将 JSON 配置转换为 RouteDefinition 对象列表
        var routeDefinitionList = JSON.parseArray(routesConfig, RouteDefinition.class);
        // 获取当前已存在的路由
        routeDefinitionLocator.getRouteDefinitions()
                .collectList()
                .subscribe(existingRoutes -> {
                    // 将 existingRoutes 转为 Map 快速查找
                    Map<String, RouteDefinition> existingRoutesMap = existingRoutes.stream()
                            .collect(Collectors.toMap(RouteDefinition::getId, Function.identity()));
                     /*
                      新增路由：如果在 existingRoutesMap 中找不到当前的 routeDefinition，则调用 addRoute(routeDefinition) 添加它。
                      更新路由：如果找到的 existingRoute 与新的 routeDefinition 不相等，则调用 updateRoute(routeDefinition) 更新它。
                      删除路由：在遍历完成后，existingRoutesMap 中剩余的路由定义都是新的 routeDefinitionList 中没有的路由，需要删除。
                    */
                    routeDefinitionList.forEach(routeDefinition -> {
                        RouteDefinition existingRoute = existingRoutesMap.remove(routeDefinition.getId());
                        if (existingRoute == null) {
                            addRoute(routeDefinition);
                        } else if (!existingRoute.equals(routeDefinition)) {
                            updateRoute(routeDefinition);
                        }
                    });

                    // 删除 Nacos 中已不存在的路由
                    existingRoutesMap.keySet().forEach(this::deleteRoute);

                    // 发布路由刷新事件，通知 Gateway 更新路由
                    publisher.publishEvent(new RefreshRoutesEvent(this));
                });
    }

    /**
     * 从 Nacos 获取路由配置
     *
     * @return 路由配置的 JSON 字符串
     */
    private String getRoutesConfigFromNacos() {
        try {
            return nacosConfigManager.getConfigService().getConfig(
                    GatewayConstant.NACOS_ROUTE_DATA_ID,
                    GatewayConstant.NACOS_ROUTER_GROUP,
                    GatewayConstant.DEFAULT_TIME
            );
        } catch (NacosException e) {
            throw new RuntimeException("从 Nacos 加载路由失败", e);
        }
    }


    /**
     * 新增路由
     */
    private void addRoute(RouteDefinition routeDefinition) {
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        log.info("新增路由：{}", routeDefinition);
    }

    /**
     * 更新路由
     */
    private void updateRoute(RouteDefinition routeDefinition) {
        routeDefinitionWriter.delete(Mono.just(routeDefinition.getId()))
                .then(Mono.defer(() -> routeDefinitionWriter.save(Mono.just(routeDefinition))))
                .subscribe();
        log.info("更新路由: {}", routeDefinition);
    }

    /**
     * 删除路由
     *
     * @param routeId 路由定义的 ID
     */
    private void deleteRoute(String routeId) {
        routeDefinitionWriter.delete(Mono.just(routeId)).subscribe();
        log.info("删除路由id: {}", routeId);
    }
}
