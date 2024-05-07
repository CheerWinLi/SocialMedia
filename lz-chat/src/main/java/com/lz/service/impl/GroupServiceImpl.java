package com.lz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.entity.Group;
import com.lz.mapper.GroupMapper;
import com.lz.result.RespResult;
import com.lz.service.GroupService;
import com.lz.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/05/07
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {


    @Autowired
    private GroupUserService groupUserService;

    @Override
    public List<Group> getGroupList(Integer groupId) {
        List<Integer> groupList = groupUserService.getGroupList(groupId);
        LambdaQueryWrapper<Group> wrapper=new LambdaQueryWrapper<>();
        wrapper.in(Group::getId,groupList);
        return baseMapper.selectList(wrapper);
    }
}