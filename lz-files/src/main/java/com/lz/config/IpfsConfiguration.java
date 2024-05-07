package com.lz.config;

import com.lz.client.IpfsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :Lictory
 * @date : 2024/04/16
 */

@Configuration
public class IpfsConfiguration {
    @Value("${Ipfs.api}")
    private String api;
    @Bean
    public IpfsClient ipfsClient() {
        return new IpfsClient(api);
    }
}
