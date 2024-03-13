package com.provedcode.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "page-config")
@Slf4j
public record PageProperties(
        int defaultPageNum,
        int defaultPageSize,
        String defaultSortBy
) {
    @PostConstruct
    void print() {
        log.info("page-props = {} ", this);
    }
}