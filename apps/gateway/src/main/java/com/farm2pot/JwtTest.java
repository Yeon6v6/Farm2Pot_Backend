package com.farm2pot;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/**
 * packageName    : com.farm2pot.jwt
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    : 테스트 용동
 */
public class JwtTest {
    public static void main(String[] args) {
        Key key = Keys.hmacShaKeyFor("6rCA7J6Q6rOgIO2GoOydtO2UhOuhnOygne2KuCBqd3Qgc2VjcmV0IO2CpOyeheuLiOuLpA==".getBytes()); // 32바이트 이상

        String token = Jwts.builder()
                .setSubject("taejin")             // username
                .claim("roles", "ROLE_USER")      // role
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1시간
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("JWT Token: " + token);
    }
}
