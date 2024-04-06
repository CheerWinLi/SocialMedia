package com.lz.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.jwt.JWTException;

import com.lz.constant.AuthProperties;
import lombok.RequiredArgsConstructor;

import com.lz.constant.WebSecurityConstant;
import com.lz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/03/05
 */

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthProperties.class)
public class AuthGlobalFilter implements GlobalFilter, Ordered {


    @Autowired
    private JwtUtil jwtUtil;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private  AuthProperties authProperties;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取request
        ServerHttpRequest request = exchange.getRequest();
        //判断是否需要拦截
        //TODO 进行路径校验

        if (isExclude(request.getPath().toString())) {
            //该路径不需要拦截,直接放行
            return chain.filter(exchange);
        }
        //获取token
        String token = null;
        List<String> headers = request.getHeaders().get(WebSecurityConstant.AUTH_HEADER);
        if (!CollUtil.isEmpty(headers)) {
            token = headers.get(0);
        }
        //解析并校验
        Long userId = null;
        try {
            //获取用户id
            //TODO 将JWT返回值改为用户Id
            //校验token
            jwtUtil.validateToken(token);
        } catch (JWTException e) {
            //token 为空或者token过期
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //前端进行登录页面的跳转以及访问

            return response.setComplete();
        }
        //传递用户信息

        //更改下游,将信息存入request中
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("payLoad", userId.toString()))
                .build();

        //放行
        return chain.filter(swe);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public boolean isExclude(String antPath) {
        //TODO 判断请求路径是否不需要拦截    首先对登录注册进行放行
        for (String excludePath : authProperties.getExcludePaths()) {
            if(antPathMatcher.match(excludePath,antPath)){
                return true;
            }
        }
        return false;
    }
}
