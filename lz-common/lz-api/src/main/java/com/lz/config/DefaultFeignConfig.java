package com.lz.config;

import cn.hutool.core.lang.Assert;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import com.lz.entity.UserContext;
import org.springframework.context.annotation.Bean;

/**
 * @author :Lictory
 * @date : 2024/03/04
 */

public class DefaultFeignConfig {
//    @Bean
//    public Logger.Level feignLogLevel(){
//        return Logger.Level.FULL;
//    }
//
//    @Bean
//    public RequestInterceptor userInfoRequestInterceptor(){
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate template) {
//                Long userId = UserContext.getUser();
//                Assert.notNull(userId,"userId为空");
//                template.header("user-info",userId.toString());
//            }
//        };
//    }
}
