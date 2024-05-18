package com.lz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author :Lictory
 * @date : 2024/04/14
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // 允许跨域访问的域名, "*"表示允许所有域
        config.addAllowedMethod("*"); // 允许所有的请求方法访问: GET, POST, DELETE, PUT等
        config.addAllowedHeader("*"); // 允许所有请求头
        source.registerCorsConfiguration("/**", config); // 注册CORS配置
        return new CorsWebFilter(source);
    }
}
