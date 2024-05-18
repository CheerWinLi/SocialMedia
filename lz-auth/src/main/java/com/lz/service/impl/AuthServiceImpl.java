package com.lz.service.impl;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.lz.entity.bo.LoginBO;
import com.lz.entity.po.User;
import com.lz.entity.vo.TokenVO;
import com.lz.service.AuthService;
import com.lz.entity.bo.RegisterBO;
import com.lz.mapper.AuthMapper;
import com.lz.constant.PayLoad;
import com.lz.constant.ResultConstant;
import com.lz.result.CommonResult;
import com.lz.util.JwtUtil;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author :Lictory
 * @date : 2024/04/05
 */

@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, User> implements AuthService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthMapper authMapper;


    @Override
    public CommonResult<TokenVO> login(LoginBO loginBO) {
        TokenVO tokenVO = new TokenVO();
        User user = authMapper.getByUsername(loginBO.getUsername());
        //判断账号是否存在
        Assert.isTrue(user != null, "账号或密码输入错误,请检查");
        //判断密码是否正确
        Assert.isTrue(passwordEncoder.matches(loginBO.getPassword(), user.getPassword()), "账号或密码输入错误,请检查");
        //更新token
        String jwtToken = jwtUtil.createJwtToken(user.getId() + "", new PayLoad().setUserId(user.getId()));
        redissonClient.getBucket(user.getId() + "").set(jwtToken);
        tokenVO.setId(user.getId()).setToken(jwtToken).setUsername(loginBO.getUsername());
        return CommonResult.successWithMsg("登陆成功", tokenVO);
    }

    @Override
    @Transactional(rollbackFor = IllegalAccessError.class)
    public CommonResult<Void> register(RegisterBO registerBO) {

        //对用户名是否重复进行检查
        User user = authMapper.getByUsername(registerBO.getUsername());
        Assert.isTrue(user == null, "用户已存在,请更换用户名");

        //将注册信息封装到use对象,利用Mps进行数据插入
        User newUser = new User()
                .setUsername(registerBO.getUsername())
                .setPassword(passwordEncoder.encode(registerBO.getPassword()))
                .setCreateTime(new Date(System.currentTimeMillis()))
                .setEmail(registerBO.getEmail())
                .setDelFlag(0);
        Assert.isTrue(authMapper.insert(newUser) > 0, "用户注册失败");
        return new CommonResult<Void>(ResultConstant.SUCCESS_CODE, ResultConstant.OPERATE_SUCCESS_MESSAGE);
    }

    @Override
    public CommonResult<Boolean> verifyUsername(String username) {
        User user = authMapper.getByUsername(username);
        if(user==null){
            return new CommonResult<Boolean>(ResultConstant.SUCCESS_CODE, ResultConstant.OPERATE_SUCCESS_MESSAGE,true);
        }else{
            return new CommonResult<Boolean>(ResultConstant.FAIL_CODE, ResultConstant.OPERATE_FAIL_MESSAGE,false);
        }
    }
}
