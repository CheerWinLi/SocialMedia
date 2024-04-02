package com.lz;

import lz.com.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author :Lictory
 * @date : 2024/03/03
 */

@MapperScan("com.lz.mapper")
@SpringBootApplication(scanBasePackages = {"lz.util","com.lz.config"})
@EnableFeignClients(defaultConfiguration = DefaultFeignConfig.class)
@EnableDiscoveryClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
