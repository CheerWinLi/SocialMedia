package com.lz.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author :Lictory
 * @date : 2024/03/10
 */
public class SecurityConfig{


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll() // 允许所有请求
                .and()
                .formLogin()
                .disable() // 禁用默认的登录功能
                .logout()
                .disable(); // 禁用默认的登出功能

        return http.build();
    }
}
