package com.lz;

import com.lz.controller.FeedController;
import com.lz.service.CommentService;
import com.lz.service.LikeService;
import com.lz.service.VideoService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author :Lictory
 * @date : 2024/04/06
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.lz.mapper"})
@EnableFeignClients
public class FeedApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeedApplication.class, args);
    }
}
