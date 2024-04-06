package com.lz.controller;

import com.lz.entity.vo.TokenVO;
import com.lz.service.AuthService;
import com.lz.entity.bo.LoginBO;
import com.lz.entity.bo.RegisterBO;
import com.lz.result.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :Lictory
 * @date : 2024/04/05
 */

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/login")
    public RespResult<TokenVO> login(@Validated @RequestBody LoginBO loginBO) {
        return authService.login(loginBO);
    }

    @RequestMapping("/register")
    public RespResult<Void> register(@Validated @RequestBody RegisterBO registerBO) {
        return authService.register(registerBO);
    }
}
