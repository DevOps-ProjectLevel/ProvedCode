package com.provedcode.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "aws-s3")
@Slf4j
public record AWSProperties(
        String accessKey,
        String secretKey,
        String region,
        String bucket
) {
    @PostConstruct
    void postCreate() {
        log.info("aws-props = {}", this);
    }
}