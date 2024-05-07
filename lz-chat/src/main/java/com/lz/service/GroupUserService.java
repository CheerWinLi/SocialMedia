package com.lz.service;

import com.lz.entity.Group;
import com.lz.entity.GroupUser;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/05/07
 */
public interface GroupUserService {

    /**
     * 通过用户id,获取群聊id
     * @param userId 用户id
     * @return 群聊id
     */
    List<Integer> getGroupList(Integer userId);
}
