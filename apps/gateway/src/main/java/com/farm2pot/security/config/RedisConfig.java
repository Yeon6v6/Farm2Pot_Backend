package com.farm2pot.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

/**
 * packageName    : com.farm2pot.config
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    :
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;


    @Bean
    @Primary
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        System.out.println("redisHost: " + redisHost);
        System.out.println("redisPort: " + redisPort);
        return new LettuceConnectionFactory(redisHost, redisPort);
    }
    @Bean
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(ReactiveRedisConnectionFactory factory) {
        ReactiveStringRedisTemplate redisTemplate = new ReactiveStringRedisTemplate(factory);
        redisTemplate.opsForValue().set("REDIS_TEST_KEY", "OK")
                .flatMap(success -> redisTemplate.opsForValue().get("REDIS_TEST_KEY"))
                .doOnNext(value -> System.out.println("[Redis Test] Value: " + value))
                .subscribe();
        return redisTemplate;
    }
}
