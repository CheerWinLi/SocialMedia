package com.lz.service;


import com.lz.entity.po.Group;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 创建群聊时默认分配一个聊天记录id = = = >需要业务同时创建record
     * @param groupName 群聊创建信息
     */
    void createGroup(String groupName);

    /**
     * 上传群聊头像
     * @param file 群聊头像
     *
     */
    void uploadImage(MultipartFile file);

    /**
     * 更新群聊名称
     * @param newGroupName 新群聊名称
     */
    void updateGroupName(String newGroupName);
}
