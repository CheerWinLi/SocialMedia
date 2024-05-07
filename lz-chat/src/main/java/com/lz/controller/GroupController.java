package com.lz.controller;

import com.lz.constant.ResultConstant;
import com.lz.entity.Group;
import com.lz.entity.GroupUser;
import com.lz.result.RespResult;
import com.lz.service.GroupService;
import com.lz.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/05/06
 */

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/list/{userId}")
    public RespResult<List<Group>>  getGroupList(@PathVariable Integer userId){
        return RespResult.successWithMsg(ResultConstant.OPERATE_SUCCESS_MESSAGE,groupService.getGroupList(userId));
    }



}
