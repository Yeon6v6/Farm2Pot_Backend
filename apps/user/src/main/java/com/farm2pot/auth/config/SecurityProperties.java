package com.farm2pot.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.farm2pot.jwt.config
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    :
 */
@Configuration
@ConfigurationProperties(prefix = "jwt.security")
@Getter
@Setter
public class SecurityProperties {
    private String key;
    private long expiration;
    private long refreshExpiration; // 선택
}
