package com.lz.util;


import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;

import com.lz.constant.WebSecurityConstant;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author :Lictory
 * @date : 2024/01/17
 */
@Component
public class JwtUtil {

    @Value("${jwt.key}")
    private String keyString;
    private byte[] key = null;
    private JWTSigner jwtSigner;

    @PostConstruct
    public void init() {
        //TODO 设置指定加密算法
        key = keyString.getBytes();
        jwtSigner = JWTSignerUtil.createSigner("HMD5", key);
    }

    public String createJwtToken(String subject, Object payload) {
        String token;
        try {
            token = JWT.create()
                    .setIssuer(WebSecurityConstant.ISSUER)
                    .setKey(key)
                    .setSubject(subject)
                    .setPayload("payload", payload)
                    .setExpiresAt(new Date(System.currentTimeMillis() + WebSecurityConstant.EXPIRE_TIME))
                    .sign(jwtSigner);
        } catch (JWTException e) {
            throw new JWTException("jwt生成异常");
        }
        return token;
    }

    public boolean validateToken(String jwt) {
        return validateDate(jwt) || validateSignature(jwt);
    }

    public boolean validateSignature(String token) {
        //验证签名算法
        boolean validSignature;
        JWT jwt = JWT.of(token);
        try {
            //验证JWT的有效性跟签名
            validSignature = jwt.setKey(key).verify(jwtSigner);
        } catch (JWTException e) {
            throw new JWTException("jwt验证签名发生异常");
        }
        return validSignature;
    }

    public boolean validateDate(String token) {
        try {
            return token != null && JWT.of(token).setKey(WebSecurityConstant.SIGN_KEY.getBytes()).validate(0);
        } catch (JWTException e) {
            return false;
        }
    }
}
