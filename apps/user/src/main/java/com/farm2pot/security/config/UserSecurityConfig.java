package com.farm2pot.security.config;

import com.farm2pot.security.filter.JwtAuthenticationFilter;
import com.farm2pot.security.service.CustomUserDetailsService;
import com.farm2pot.security.service.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * packageName    : com.farm2pot.auth.config
 * author         : TAEJIN
 * date           : 2025-10-03
 * description    :
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class UserSecurityConfig {

    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService; // Spring Security에서 사용자 조회용
    private final SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager 수동 등록
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return new ProviderManager(provider);
    }

    // SecurityFilterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        List<String> whiteList = securityProperties.getWhiteList();
        http
                .csrf(csrf -> csrf.disable()) // REST API라 CSRF 비활성화
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 X
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(whiteList.toArray(new String[0])
                        ).permitAll()  // 로그인/회원가입/재발급 허용
                        .anyRequest().authenticated()             // 나머지 요청은 인증 필요
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtProvider, userDetailsService, securityProperties);
    }
}