package com.farm2pot.jwt.filter;

import com.farm2pot.security.service.JwtProvider;
import org.apache.ibatis.logging.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

@WebFluxTest
public class JwtAuthenticationFilterTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private ReactiveStringRedisTemplate redisTemplate;

    private String validToken;
    private Long id = 1L;

    @BeforeEach
    void setUp() {
        validToken = "valid.jwt.token";

        Mockito.when(jwtProvider.validateToken(validToken)).thenReturn(true);
        Mockito.when(jwtProvider.getId(validToken)).thenReturn(id.toString());
        Mockito.when(jwtProvider.getRoles(validToken)).thenReturn(List.of("ROLE_USER"));

        Mockito.when(redisTemplate.hasKey("BL:" + validToken)).thenReturn(Mono.just(false));
    }

    @Test
    void authApi_withoutToken_shouldPass() {
        webTestClient.get()
                .uri("/auth/login")
                .exchange()
                .expectStatus().isOk(); // permitAll 확인
    }

    @Test
    void privateApi_withoutToken_shouldReturn401() {
        webTestClient.get()
                .uri("/api/private/data")
                .exchange()
                .expectStatus().isUnauthorized(); // JWT 없으면 차단
    }

    @Test
    void privateApi_withValidToken_shouldPass() {
        webTestClient.get()
                .uri("/api/private/data")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + validToken)
                .exchange()
                .expectStatus().isOk(); // JWT 통과
    }

    @Test
    void privateApi_withBlacklistedToken_shouldReturn401() {
        Mockito.when(redisTemplate.hasKey("BL:" + validToken)).thenReturn(Mono.just(true));

        webTestClient.get()
                .uri("/api/private/data")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + validToken)
                .exchange()
                .expectStatus().isUnauthorized(); // 블랙리스트 차단
    }
}
