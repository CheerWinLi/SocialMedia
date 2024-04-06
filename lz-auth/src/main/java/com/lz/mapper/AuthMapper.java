package com.lz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.lz.entity.po.User;
import org.springframework.stereotype.Repository;

/**
 * @author :Lictory
 * @date : 2024/04/05
 */

@Repository
public interface AuthMapper extends BaseMapper<User> {


    /**
     * 通过用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User getByUsername(String username);

}
