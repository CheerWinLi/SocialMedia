package com.lz.service;

import com.lz.entity.po.Record;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/06/01
 */
public interface RecordService {

    /**
     * 保存聊天记录
     *
     * @param record 聊天记录
     * @return 是否保存成功
     */
    Boolean saveRecord(Record record);

    /**
     * 获取离线聊天记录
     * @return 离线聊天记录
     */
    List<Record> getOffLineRecord(Integer receiverId);
}
