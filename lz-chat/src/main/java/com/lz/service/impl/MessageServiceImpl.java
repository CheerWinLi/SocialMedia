package com.lz.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.constant.TopicConstant;
import com.lz.entity.po.Message;
import com.lz.entity.po.Record;
import com.lz.mapper.MessageMapper;
import com.lz.producer.MessageProducer;
import com.lz.service.MessageService;
import com.lz.service.RecordService;
import com.lz.ws.WebSocketService;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :Lictory
 * @date : 2024/06/01
 */

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RecordService recordService;

    @Override
    public void sendOffLineMessage(Message message) {
        MessageProducer.sendMessage(rocketMQTemplate, TopicConstant.MESSAGE_TOPIC, TopicConstant.MESSAGE_TOPIC, message);
    }

    @Override
    public void sendOnLineMessage(Message message) {
    }

    @Override
    public List<Message> getOffLineMessage(Integer receiverId) {
        return recordService.getOffLineRecord(receiverId)
                .stream()
                .map(record -> new Message()
                        .setMessage(record.getMessage())
                        .setSendTime(record.getCreateTime())
                        .setReceiver(record.getReceiver())
                        .setSender(record.getSender()))
                .collect(Collectors.toList());
    }
}
