package com.lz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author :Lictory
 * @date : 2024/03/04
 */
@SpringBootApplication(scanBasePackages = {"lz.util","com.lz.config"})
@EnableDiscoveryClient
@EnableWebFlux
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
