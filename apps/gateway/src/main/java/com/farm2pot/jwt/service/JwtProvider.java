package com.farm2pot.jwt.service;

import com.farm2pot.jwt.config.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.farm2pot.util
 * author         : TAEJIN
 * date           : 2025-10-03
 * description    :
 */


@Component
@RequiredArgsConstructor
public class JwtProvider {
    private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);
    private final SecurityProperties securityProperties;
    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(securityProperties.getKey());
        this.key = Keys.hmacShaKeyFor(keyBytes);

    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            log.warn("[JWT] validation failed: {}", e.getMessage());
            return false;
        }
    }

    public String getLoginId(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<String> getRoles(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Object rolesObj = claims.get("roles");
            if (rolesObj == null) {
                return Collections.emptyList();
            }

            if (rolesObj instanceof List) {
                // 리스트 요소들을 문자열로 변환
                return ((List<?>) rolesObj).stream()
                        .map(Object::toString)
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
            }

            if (rolesObj instanceof String) {
                String roles = ((String) rolesObj).trim();
                if (roles.isEmpty()) return Collections.emptyList();
                return Arrays.stream(roles.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
            }

            // 그 외 타입일 경우 안전하게 toString 처리
            return Collections.singletonList(rolesObj.toString());
        } catch (JwtException | IllegalArgumentException e) {
            // 토큰이 유효하지 않거나 파싱 실패 시
            log.warn("Failed to parse token roles: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}