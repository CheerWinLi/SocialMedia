package com.lz.service;

import com.lz.entity.bo.LoginBO;
import com.lz.entity.bo.RegisterBO;
import com.lz.entity.vo.RegisterVO;
import com.lz.entity.vo.TokenVO;

import lz.result.RespResult;
import org.springframework.stereotype.Service;

/**
 * @author :Lictory
 * @date : 2024/03/03
 */
@Service
public interface UserService {

    /**
     * 用户正常登录
     * @param loginBO 登录BO
     * @return 结果集
     */
     RespResult<TokenVO> login(LoginBO loginBO);

    /**
     * 用于用户注册
     * @param registerBO 注册BO
     * @return 返回携带token的对象
     */
     RespResult<RegisterVO> register(RegisterBO registerBO);
}
