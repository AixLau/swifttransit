package com.swifttranist.gateway.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 用于处理未认证用户的访问请求。
 * 当用户未提供有效的认证信息时，将触发该处理逻辑。
 */
@Component
public class JwtAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    /**
     * 当用户尝试访问受保护的资源但未通过认证时调用该方法。
     * 它设置响应状态为 401 未授权，并返回 JSON 格式的错误消息。
     *
     * @param exchange 当前的 ServerWebExchange，包含 HTTP 请求和响应
     * @param ex       触发该方法的 AuthenticationException 异常
     * @return 一个 Mono<Void>，表示响应完成
     */
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        // 设置 HTTP 响应状态为 401 未授权
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        // 设置响应内容类型为 JSON
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        // 构建错误消息
        byte[] bytes = "{\"error\": \"Unauthorized\", \"message\": \"Authentication is required\"}"
                .getBytes(StandardCharsets.UTF_8);
        // 将错误消息写入响应体
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }
}
