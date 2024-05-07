package com.lz.client;

//
//import lz.com.config.DefaultFeignConfig;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;

import com.lz.config.DefaultFeignConfig;


import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author :Lictory
 * @date : 2024/03/04
 */

@FeignClient(value = "lz-user")
public interface UserClient {

}
