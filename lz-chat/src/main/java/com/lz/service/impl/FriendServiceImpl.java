package com.lz.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lz.entity.vo.FriendVo;
import com.lz.service.FriendService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/05/08
 */

@Service
public class FriendServiceImpl implements FriendService {


    @Override
    public Boolean addFriend(Integer userId, Integer friendId) {
        return null;
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
