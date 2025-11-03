package com.farm2pot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins( /* 4001 : user, 4002 : admin */
                        // prod
                        "http://user-service:4001",
                        "http://admin-service:4002",
                        // dev
                        "http://localhost:14001",
                        "http://localhost:14002"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        // 기본적으로 JSON/Jackson 코덱은 자동 등록됨
        // 필요하다면 추가 커스터마이징 가능
    }
}