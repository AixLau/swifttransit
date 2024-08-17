package com.swifttranist.gateway.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 读取 Nacos 配置项，用于配置监听项
 */
@Configuration

public class GatewayConstant {

    /**
     * 默认超时时间 30s
     */
    public static long DEFAULT_TIME = 30000;

    /**
     * Nacos 服务器地址
     */
    public static String NACOS_SERVER_ADDR;

    /**
     * 命名空间
     */
    public static String NACOS_NAMESPACE;

    /**
     * data-id
     */
    public static String NACOS_ROUTE_DATA_ID;

    /**
     * 分组 id
     */
    public static String NACOS_ROUTER_GROUP;

    @Value("${spring.cloud.nacos.server-addr}")
    public void setNacosServerAddr(String nacosServerAddr) {
        NACOS_SERVER_ADDR = nacosServerAddr;
    }

    @Value("${spring.cloud.nacos.discovery.namespace}")
    public void setNacosNamespace(String nacosNamespace) {
        NACOS_NAMESPACE = nacosNamespace;
    }

    @Value("${nacos.gateway.route.config.data-id}")
    public void setNacosRouteDataId(String nacosRouteDataId) {
        NACOS_ROUTE_DATA_ID = nacosRouteDataId;
    }

    @Value("${nacos.gateway.route.config.group}")
    public void setNacosRouterGroup(String nacosRouterGroup) {
        NACOS_ROUTER_GROUP = nacosRouterGroup;
    }
}
