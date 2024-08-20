package com.swifttranist.gateway.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 用于处理已认证用户但无访问权限的请求。
 * 当用户尝试访问无权限的资源时，将触发该处理逻辑。
 */
@Component
public class JwtAccessDeniedHandler implements ServerAccessDeniedHandler {

    /**
     * 当用户已认证但无权限访问某个资源时调用该方法。
     * 它设置响应状态为 403 禁止访问，并返回 JSON 格式的错误消息。
     *
     * @param exchange 当前的 ServerWebExchange，包含 HTTP 请求和响应
     * @param denied   触发该方法的 AccessDeniedException 异常
     * @return 一个 Mono<Void>，表示响应完成
     */
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        // 设置 HTTP 响应状态为 403 禁止访问
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        // 设置响应内容类型为 JSON
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        // 构建错误消息
        byte[] bytes = "{\"error\": \"Forbidden\", \"message\": \"Access is denied\"}"
                .getBytes(StandardCharsets.UTF_8);
        // 将错误消息写入响应体
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }
}