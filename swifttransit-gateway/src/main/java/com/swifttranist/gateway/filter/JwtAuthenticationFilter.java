package com.swifttranist.gateway.filter;

import com.aix.swifttransit.common.core.constant.CommonConstants;
import com.aix.swifttransit.common.core.util.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    private static SecurityContext getSecurityContext(String username) {
        UserDetails user = new User(username, "", Collections.emptyList());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                /*
                 创建一个 SecurityContext 对象来存储上述的认证对象，
                 并将这个安全上下文放入到 Reactor 的上下文中
                 （使用 ReactiveSecurityContextHolder.withSecurityContext）。
                 然后继续链式处理过滤器链中的下一个过滤器。
                 */
        return new SecurityContextImpl(authentication);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (!ObjectUtils.isEmpty(authHeader) && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            boolean validateAccessToken;
            try {
                validateAccessToken = JwtUtil.validateAccessToken(token);
            } catch (JwtException | IllegalArgumentException | MalformedJwtException e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            if (validateAccessToken) {
                String username = JwtUtil.getUsername(token);
                return redisTemplate.opsForValue().get(CommonConstants.REFRESH_TOKEN_KEY + username)
                        .flatMap(refreshToken -> {
                            if (ObjectUtils.isEmpty(refreshToken)) {
                                // Redis 中不存在 RefreshToken，返回401
                                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                                return exchange.getResponse().setComplete();
                            } else {
                                SecurityContext context = getSecurityContext(username);
                                return chain.filter(exchange)
                                        .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));

                            }
                        });
            }
        }
        return chain.filter(exchange);
    }
}

