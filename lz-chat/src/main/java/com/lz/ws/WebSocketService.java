package com.lz.ws;

import com.alibaba.fastjson.JSON;
import com.lz.entity.Message;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :Lictory
 * @date : 2024/04/29
 */

@ServerEndpoint(value = "/chat/{username}")
@Component
public class WebSocketService {


    private static final Map<String, Session> onLineUsers = new ConcurrentHashMap<>();

//    private Session session;
//    /**
//     * 当前session的用户标识(key)
//     */
    private String username;

    /**
     * 建立websocket连接后被调用
     *
     * @param session session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "username") String username) {
        //保存session TODO
//        this.session = session;
        this.username = username;
        onLineUsers.put(username, session);
        //广播消息
        //包含当前所有user对象
        broadcastAllUsers(onLineUsers.keySet().toString());
        //发送消息
    }


    private void broadcastAllUsers(String message) {
        for (Map.Entry<String, Session> entry : onLineUsers.entrySet()) {
            //获取所有用户的session
            entry.getValue().getAsyncRemote().sendText(message);
        }
    }

    /**
     * 客户端发送消息到达服务端时调用
     *
     * @param messageJson 消息
     */
    @OnMessage
    public void onMessage(String messageJson) {
        //将消息推送给指定的用户
        Message message = JSON.parseObject(messageJson, Message.class);

        //获取接收方的session
        Session session = onLineUsers.get(message.getToUserName());
        Message post = new Message()
                .setMessage(message.getMessage())
                .setToUserName(message.getToUserName());
        session.getAsyncRemote().sendText(JSON.toJSONString(post));
    }

    /**
     * 断开Websocket连接时调用
     *
     * @param session session
     */
    @OnClose
    public void onClose(Session session) {
        //从map中剔除当前session对象
        onLineUsers.remove(this.username);
        //广播消息,当前用户下线
        broadcastAllUsers(onLineUsers.keySet().toString());
    }
}
