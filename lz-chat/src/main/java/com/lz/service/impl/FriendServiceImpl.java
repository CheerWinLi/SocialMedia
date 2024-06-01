package com.lz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.entity.po.Friend;
import com.lz.entity.vo.FriendVo;
import com.lz.mapper.FriendMapper;
import com.lz.service.FriendService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/05/08
 */

@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {


    @Override
    public Boolean addFriend(Integer userId, Integer friendId) {
        Assert.isTrue(baseMapper.insert(new Friend(userId, friendId, 0)) > 0, "add friend fail");
        return true;
    }

    @Override
    public Boolean deleteFriend(Integer userId, Integer friendId) {
        return null;
    }

    @Override
    public Boolean updateFriend(Integer userId, Integer friendId) {
        return null;
    }

    @Override
    public List<FriendVo> getFriendList(Integer userId) {
        return null;
    }
}
