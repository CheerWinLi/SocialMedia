package com.lz.service;

import com.lz.entity.vo.FriendVo;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/05/08
 */
public interface FriendService {

    Boolean addFriend(Integer userId, Integer friendId);

    Boolean deleteFriend(Integer userId, Integer friendId);

    Boolean updateFriend(Integer userId, Integer friendId);

    /**
     * 通过用户id获取好友列表
     * @param userId userId
     * @return list
     */
    List<FriendVo> getFriendList(Integer userId);


}
