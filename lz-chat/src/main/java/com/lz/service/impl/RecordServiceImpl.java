package com.lz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.constant.BusinessConstant;
import com.lz.entity.po.Record;
import com.lz.mapper.RecordMapper;
import com.lz.service.RecordService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/06/01
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Override
    public Boolean saveRecord(Record record) {
        Assert.isTrue(baseMapper.insert(record) > 0, "聊天记录存入数据库失败");
        return true;
    }

    @Override
    public List<Record> getOffLineRecord(Integer receiverId) {
        LambdaQueryWrapper<Record> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Record::getReceiver,receiverId)
                .eq(Record::getSend, BusinessConstant.UN_SEND);
        return baseMapper.selectList(wrapper);
    }
}
