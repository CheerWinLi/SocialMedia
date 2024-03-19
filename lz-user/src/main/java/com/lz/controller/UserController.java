package com.lz.controller;

import com.lz.entity.bo.LoginBO;
import com.lz.entity.bo.RegisterBO;
import com.lz.entity.po.User;
import com.lz.entity.vo.RegisterVO;
import com.lz.entity.vo.TokenVO;
import com.lz.entity.vo.UserVO;
import com.lz.mapper.UserMapper;
import com.lz.result.RespResult;
import com.lz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author :Lictory
 * @date : 2024/03/03
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    public RespResult<TokenVO> login(@Validated @RequestBody LoginBO loginBO) {
        return userService.login(loginBO);
    }

    public RespResult<RegisterVO> register(@Validated @RequestBody RegisterBO registerBO) {
        return userService.register(registerBO);
    }

}
