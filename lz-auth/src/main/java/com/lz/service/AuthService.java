package com.lz.service;


import com.lz.entity.bo.LoginBO;
import com.lz.entity.bo.RegisterBO;
import com.lz.entity.vo.TokenVO;
import com.lz.result.RespResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author :Lictory
 * @date : 2024/04/05
 */

public interface AuthService {
    /**
     *  登录
     * @param loginBO 登录
     * @return  携带token
     */
    RespResult<TokenVO> login (@RequestBody LoginBO loginBO);

    /**
     * 注册
     * @param registerBO 注册信息
     * @return 返回操作信息
     */
    RespResult<Void> register(@RequestBody RegisterBO registerBO);

    /**
     * 检查用户名是否重复
     * @param username 注册的用户名
     * @return bool
     */
    RespResult<Boolean> verifyUsername(@RequestParam("username") String username);

}
