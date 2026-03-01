package com.netroutines.lms.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record ProfileProperties(boolean debug) {
}
