package com.provedcode.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@AllArgsConstructor
public class S3Config {
    AWSProperties awsProperties;
    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(awsProperties.accessKey(), awsProperties.secretKey());

        return AmazonS3ClientBuilder.standard()
                .withRegion(awsProperties.region())
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    @PostConstruct
    void test() {
        log.info("S3Config: %s %s %s".formatted(awsProperties.accessKey(), awsProperties.secretKey(), awsProperties.region()));
    }
}