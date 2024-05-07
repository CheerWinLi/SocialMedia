package com.lz;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author :Lictory
 * @date : 2024/04/04
 */

@SpringBootApplication(scanBasePackages ={"com.lz","com.lz.controller","com.lz.util","com.lz.config"} )
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lz.mapper")
@Slf4j

//@EnableWebFlux
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
