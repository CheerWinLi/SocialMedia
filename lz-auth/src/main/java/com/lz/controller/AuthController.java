package com.lz.controller;

import com.lz.entity.vo.TokenVO;

import com.lz.entity.bo.LoginBO;
import com.lz.entity.bo.RegisterBO;
import com.lz.result.CommonResult;

import com.lz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author :Lictory
 * @date : 2024/04/05
 */

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public CommonResult<TokenVO> login(@Validated @RequestBody LoginBO loginBO) {
        return authService.login(loginBO);
    }

    @PostMapping("/register")
    public CommonResult<Void> register(@Validated @RequestBody RegisterBO registerBO) {
        return authService.register(registerBO);
    }

    @PostMapping("/verifyUsername")
    public CommonResult<Boolean> verifyUsername(@RequestParam String username) {
        return authService.verifyUsername(username);
    }

}





