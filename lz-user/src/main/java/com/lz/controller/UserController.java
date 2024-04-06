package com.lz.controller;

import com.lz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
