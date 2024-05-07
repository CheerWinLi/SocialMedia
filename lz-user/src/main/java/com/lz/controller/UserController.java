package com.lz.controller;

import com.lz.result.RespResult;
import com.lz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author :Lictory
 * @date : 2024/03/03
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;




}
