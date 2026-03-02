package com.netroutines.lms.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application")
public record AppProperties(String name) {
}
