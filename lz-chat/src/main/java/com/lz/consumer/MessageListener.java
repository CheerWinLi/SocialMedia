package com.lz.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lz.constant.BusinessConstant;
import com.lz.constant.GroupConstant;
import com.lz.constant.TopicConstant;
import com.lz.entity.po.Message;
import com.lz.entity.po.Record;
import com.lz.service.RecordService;
import com.lz.ws.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author :Lictory
 * @date : 2024/05/31
 */

@Component
@Slf4j
@RocketMQMessageListener(topic = TopicConstant.MESSAGE_TOPIC, consumerGroup = GroupConstant.MESSAGE_GROUP, selectorExpression = TopicConstant.MESSAGE_TOPIC)
public class MessageListener implements RocketMQListener<MessageExt> {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RecordService recordService;

    @Override
    public void onMessage(MessageExt messageExt) {
        //用消息id作为key存入Redis防止消息重复消费
        String msgId = messageExt.getMsgId();
        if (redissonClient.getBucket(BusinessConstant.PERSONAL_MESSAGE + msgId).isExists()) {
            //key已存在,代表消息已经消费,不处理
            return;
        } else {
            redissonClient.getBucket(BusinessConstant.PERSONAL_MESSAGE + msgId)
                    .set(messageExt);
            //key不存在,代表消息未消费,处理消息
            String jsonString=new String(messageExt.getBody());
            //json转object(Message)
            Message message = JSONObject.toJavaObject(JSON.parseObject(jsonString), Message.class);
            //判断消息接受者是否在线
            Record record = new Record();
            if (WebSocketService.ONLINE_USERS.containsKey(message.getReceiver())) {
                //在线,发送消息
                WebSocketService.ONLINE_USERS.get(message.getReceiver())
                        .getAsyncRemote()
                        .sendText(JSON.toJSONString(message));
                record.setSend(BusinessConstant.SEND);
            } else {
                //不在线
                record.setSend(BusinessConstant.UN_SEND);
            }
            //存入历史聊天数据数据库
            record.setCreateTime(message.getSendTime()).
                    setReceiver(message.getReceiver())
                    .setMessage(message.getMessage())
                    .setSender(message.getSender());
            Assert.isTrue(recordService.saveRecord(record), "存入历史聊天数据数据库失败");
        }
    }
}
