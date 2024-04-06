package com.lz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author :Lictory
 * @date : 2024/04/04
 */

@SpringBootApplication(scanBasePackages ={"com.lz.util","com.lz.config"} )
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan()
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
