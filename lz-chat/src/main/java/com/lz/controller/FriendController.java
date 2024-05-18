package com.lz.controller;

import com.lz.entity.vo.FriendVo;
import com.lz.result.CommonResult;
import com.lz.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2024/05/08
 */

@RestController
@RequestMapping("/api/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;


    @GetMapping("/list/{userId}")
    public CommonResult<List<FriendVo>> getFriendList(@PathVariable Integer userId) {
        return CommonResult.autoResult(true, friendService.getFriendList(userId));
    }

    @PostMapping("/{userId}/{targetId}")
    public CommonResult<Boolean> addFriend(@PathVariable Integer userId, @PathVariable Integer targetId) {
        return CommonResult.autoResult(friendService.addFriend(userId, targetId));
    }

}
