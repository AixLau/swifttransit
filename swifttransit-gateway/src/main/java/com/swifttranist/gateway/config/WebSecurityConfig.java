package com.swifttranist.gateway.config;

import com.swifttranist.gateway.filter.JwtAuthenticationFilter;
import com.swifttranist.gateway.security.JwtAccessDeniedHandler;
import com.swifttranist.gateway.security.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    private final String[] requestMatchers = {
            "/auth/auth/login",
            "/doc.html",
            "/webjars/**",
            "/js/**",       // 放行js文件
            "/css/**",      // 放行css文件
            "/images/**",   // 放行图片文件
            "/favicon.ico", // 放行favicon图标
            "/v3/**",
            "/auth/v3/api-docs/**",
            "/user/v3/api-docs/**"
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource())) // 配置CORS
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // 禁用 CSRF 保护
                .authorizeExchange(auth -> auth
                        .pathMatchers(requestMatchers).permitAll() // 允许访问接口
                        .anyExchange().authenticated() // 其他接口需要认证
                )
                .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .exceptionHandling(exceptionHandlingSpec -> exceptionHandlingSpec
                        // .authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED)) // 401 未授权
                        // .accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.FORBIDDEN)) // 403 禁止访问
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 401 未授权
                        .accessDeniedHandler(jwtAccessDeniedHandler) // 403 禁止访问

                );
        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*")); // 允许所有域名
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // 允许的HTTP方法
        config.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type")); // 允许的头
        config.setAllowCredentials(true); // 是否允许发送凭证（如 Cookie）

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 应用于所有路径
        return source;
    }
}
