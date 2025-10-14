package com.farm2pot.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * packageName    : com.farm2pot.jwt.config
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    : security 관련
 */
@Component
@Getter @Setter
@ConfigurationProperties(prefix = "jwt.security")
public class SecurityProperties {

    private List<String> whiteList;
    private String key;
}
