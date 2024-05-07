package com.lz.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :Lictory
 * @date : 2024/05/06
 */

@Configuration
public class MinioConfig {
    @Value("${minio.bucket1.endpoint}")
    private String endpoint;

    @Value("${minio.bucket1.port}")
    private int port;

    @Value("${minio.bucket1.accessKey}")
    private String accessKey;

    @Value("${minio.bucket1.secretKey}")
    private String secretKey;

    @Value("${minio.bucket1.bucket}")
    private String bucket;

    @Value("${minio.bucket1.publicEndpoint}")
    private String publicEndpoint;
    @Bean(name = "MinioClient")
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint,port,false)
                .credentials(accessKey, secretKey)
                .build();
    }
}
