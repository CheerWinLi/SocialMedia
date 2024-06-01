package com.lz.service;

import com.lz.entity.po.Message;
import com.lz.entity.po.Record;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/06/01
 */


public interface MessageService {
     void sendOffLineMessage(Message message);

     void sendOnLineMessage(Message message);

     List<Message> getOffLineMessage(Integer receiverId);
}
