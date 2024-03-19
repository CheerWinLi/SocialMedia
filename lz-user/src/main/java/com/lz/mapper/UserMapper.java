package com.lz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author :Lictory
 * @date : 2024/03/04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    /**
     * 通过username查询用户
     *
     * @param username 用户名
     * @return user
     */
    User getByUsername(String username);

}
