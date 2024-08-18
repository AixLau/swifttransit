package com.swifttranist.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

/**
 * 用于计算请求耗时的过滤器。
 */
@Component
@Slf4j
public class RequestTimeGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 记录请求开始时间
        Instant startTime = Instant.now();

        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    // 请求完成后计算耗时
                    Instant endTime = Instant.now();
                    var elapsedTime = Duration.between(startTime, endTime).toMillis();
                    log.info("请求URL：{}took {}ms", exchange.getRequest().getURI(), elapsedTime);
                })
        );
    }

    @Override
    public int getOrder() {
        return -1; // 优先级，越小越先执行
    }
}
