package com.lz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.entity.po.GroupUser;
import com.lz.mapper.GroupUserMapper;
import com.lz.service.GroupUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :Lictory
 * @date : 2024/05/07
 */

@Service
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements GroupUserService {

    @Override
    public List<Integer> getGroupList(Integer userId) {
        //根据userId在GroupUser中间表中获取对应的groupId
        LambdaQueryWrapper<GroupUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GroupUser::getUserId, userId);
        List<GroupUser> groupUsers = this.list(queryWrapper);
        //根据groupUsers获取groupId,存入List
        List<Integer> groupIds = groupUsers.stream().map(GroupUser::getGroupId).collect(Collectors.toList());
        return groupIds;
    }
}
