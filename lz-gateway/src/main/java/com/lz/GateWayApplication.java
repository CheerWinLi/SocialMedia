package com.lz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author :Lictory
 * @date : 2024/03/04
 */
@SpringBootApplication(scanBasePackages = {"com.lz.util","com.lz.constant","com.lz"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties
@EnableWebFlux
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
