package com.lz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lz.entity.bo.LoginBO;
import com.lz.entity.bo.RegisterBO;
import com.lz.entity.po.User;
import com.lz.entity.vo.RegisterVO;
import com.lz.entity.vo.TokenVO;
import com.lz.mapper.UserMapper;
import com.lz.service.UserService;
import lz.constant.PayLoad;
import lz.constant.ResultConstant;
import lz.result.RespResult;
import lz.util.JwtUtil;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author :Lictory
 * @date : 2024/03/04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private JwtUtil jwtUtil;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public RespResult<TokenVO> login(LoginBO loginBO) {
        TokenVO tokenVO = new TokenVO();
        User user = userMapper.getByUsername(loginBO.getUsername());
        //判断账号是否存在
        Assert.isTrue(user != null, "账号或密码输入错误,请检查");
        //判断密码是否正确
        Assert.isTrue(user.getPassword().equals(passwordEncoder.encode(loginBO.getPassword())), "账号或密码输入错误,请检查");
        //更新token
        String jwtToken = jwtUtil.createJwtToken(user.getId() + "", new PayLoad().setUserId(user.getId()));
        redissonClient.getBucket(user.getId() + "").set(jwtToken);
        tokenVO.setId(user.getId()).setToken(jwtToken).setUsername(loginBO.getUsername());
        return RespResult.successWithMsg("登陆成功", tokenVO);
    }

    @Override
    @Transactional(rollbackFor = IllegalAccessError.class)
    public RespResult<RegisterVO> register(RegisterBO registerBO) {

        //对用户名是否重复进行检查
        User byUsername = userMapper.getByUsername(registerBO.getUsername());
        Assert.isTrue(byUsername == null, "用户已存在,请更换用户名");

        //将注册信息封装到use对象,利用Mps进行数据插入
        User newUser = new User()
                .setUsername(registerBO.getUsername())
                .setPassword(passwordEncoder.encode(registerBO.getPassword()))
                .setCreateTime(new Date(System.currentTimeMillis()))
                .setEmail(registerBO.getEmail())
                .setDelFlag(0);
        Assert.isTrue(userMapper.insert(newUser) > 0, "用户注册失败");
        return new RespResult<RegisterVO>(ResultConstant.SUCCESS_CODE, ResultConstant.OPERATE_SUCCESS_MESSAGE, new RegisterVO().setMessage("注册成功").setResult(true));
    }
}
