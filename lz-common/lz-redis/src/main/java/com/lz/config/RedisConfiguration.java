package com.lz.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :Lictory
 * @date : 2024/03/31
 */

@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.address}")
    private String address;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setPassword(password).setAddress(address);
        return Redisson.create(config);
    }
}
