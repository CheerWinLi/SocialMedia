package com.lz.service;


import com.lz.entity.Group;
import com.lz.result.RespResult;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/05/07
 */
public interface GroupService {

    /**
     * 通过群聊主键获取群聊信息
     * @param groupId 群聊主键
     * @return 群聊信息
     */
    List<Group> getGroupList(Integer groupId);

}
