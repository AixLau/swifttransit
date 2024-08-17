package com.swifttransit.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final String[] requestMatchers = {
            "/login"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource())) // 配置CORS
                .csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF 保护
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(requestMatchers).permitAll() // 允许访问接口
                        .anyRequest().authenticated() // 其他接口需要认证
                );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var config = new CorsConfiguration();

        // config.setAllowedOrigins(List.of("http://example.com", "http://anotherdomain.com"));
        config.setAllowedOriginPatterns(List.of("*")); // 允许所有域名
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));// 允许http方法
        config.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));  // 允许的头
        config.setAllowCredentials(true);  // 是否允许发送凭证（如 Cookie）

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);  // 应用于所有路径
        return source;
    }
}