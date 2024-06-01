package com.lz.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

/**
 * @author :Lictory
 * @date : 2024/05/31
 */
@Component
@Slf4j
public class MessageProducer {
    public static <T> void sendMessage(RocketMQTemplate rocketMQTemplate,String topic, String tag, T message) {
        rocketMQTemplate.asyncSend(topic + ":" + tag, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("异步消息发送成功");
            }
            @Override
            public void onException(Throwable throwable) {
                log.error("异步消息发送失败");
            }
        });
    }
}
