package com.horqian.basic.config;

import io.minio.MinioClient;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author macro
 * @create 2021-03-05-14:07
 **/
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {
    private String endpoint;

    private int port;

    private String accessKey;

    private String secretKey;

    private String bucketName;

    @SneakyThrows
    @Bean
    public MinioClient getMinioClient() {
        MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
        return minioClient;
    }
}
