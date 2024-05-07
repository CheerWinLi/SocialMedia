package com.lz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lz.client.FileClient;
import com.lz.entity.po.User;
import com.lz.mapper.UserMapper;
import com.lz.result.RespResult;
import com.lz.service.UserService;
import com.lz.util.JwtUtil;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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


}
