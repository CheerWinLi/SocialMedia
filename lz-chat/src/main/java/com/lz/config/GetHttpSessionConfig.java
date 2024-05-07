package com.lz.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author :Lictory
 * @date : 2024/04/29
 */
@Configuration
public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {
    /**
     *
     * @param sec 配置对象
     * @param request 握手请求对象
     * @param response 握手响应对象
     */

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        //获取HttpSession
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        //将httpsession保存
        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
    }
}
