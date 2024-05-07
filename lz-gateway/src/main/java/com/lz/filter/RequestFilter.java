package com.lz.filter;

import com.lz.constant.WebSecurityConstant;
import com.lz.result.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author :Lictory
 * @date : 2024/04/14
 */
@Slf4j
@Component
@Order(-3)
public class RequestFilter implements GlobalFilter{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        log.info("RequestFilter执行");
        log.info("收到一次请求");
        log.info("Request Method: " + request.getMethodValue());
        log.info("Request URI: " + request.getURI());
        log.info("Request Headers: " + request.getHeaders());
        log.info("Request Query Params: " + request.getQueryParams());
        return chain.filter(exchange);
    }
}

