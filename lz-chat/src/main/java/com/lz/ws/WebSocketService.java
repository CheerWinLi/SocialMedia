package com.lz.ws;

import com.alibaba.fastjson.JSON;
import com.lz.constant.BusinessConstant;
import com.lz.entity.po.Message;
import com.lz.service.MessageService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :Lictory
 * @date : 2024/04/29
 */
@Component
@ServerEndpoint("/chat/{userId}")
@Data
@Slf4j
public class WebSocketService {

    public static final Map<String, Session> ONLINE_USERS = new ConcurrentHashMap<>();

    private String username;

    private static MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        WebSocketService.messageService = messageService;
    }

    /**
     * 建立websocket连接后被调用
     *
     * @param session session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String username) {
        //保存session
        this.username = username;
        ONLINE_USERS.put(username, session);
        // TODO 查看是否有未读消息
        List<Message> offLineMessage = messageService.getOffLineMessage(Integer.parseInt(username));
        if (offLineMessage == null) {
            //没有未读
            return;
        } else {
            for (Message message : offLineMessage) {
                //遍历发送
                session.getAsyncRemote().sendText(JSON.toJSONString(message));
            }
        }
    }

    private void broadcastAllUsers(String message) {
        for (Map.Entry<String, Session> entry : ONLINE_USERS.entrySet()) {
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
        message.setSendTime(new Date(System.currentTimeMillis()));
        //获取接收方的session
        if (!ONLINE_USERS.containsKey(message.getReceiver())) {
            //用户离线
            messageService.sendOffLineMessage(message);
        } else {
            Session session = ONLINE_USERS.get(message.getReceiver());
            Message post = new Message()
                    .setSender(message.getSender())
                    .setMessage(message.getMessage())
                    .setReceiver(message.getReceiver())
                    .setTag(BusinessConstant.PERSONAL_MESSAGE);
            session.getAsyncRemote().sendText(JSON.toJSONString(post));
        }
    }

    /**
     * 断开Websocket连接时调用
     *
     * @param session session
     */
    @OnClose
    public void onClose(Session session) {
        //从map中剔除当前session对象
        ONLINE_USERS.remove(this.username);
//        广播消息,当前用户下线
//        broadcastAllUsers(onLineUsers.keySet().toString());
    }


}
