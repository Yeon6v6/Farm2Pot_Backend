package com.farm2pot.security.filter;

import com.farm2pot.security.config.SecurityProperties;
import com.farm2pot.security.service.CustomUserDetailsService;
import com.farm2pot.security.service.JwtProvider;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * packageName    : com.farm2pot.auth.filter
 * author         : TAEJIN
 * date           : 2025-10-03
 * description    : UserService -- jwt filter
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService;
    private final SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        // 화이트리스트 경로 조회
        List<String> whiteList = securityProperties.getWhiteList();

        // 패턴 매칭 (예: AntPathMatcher)
        AntPathMatcher pathMatcher = new AntPathMatcher();
        boolean isWhitelisted = whiteList.stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, path));

        // 화이트리스트에 포함된 경우, JWT 검증 생략
        if (isWhitelisted) {
            log.debug("[JWT FILTER] Skipped authentication for path: {}", path);
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = getJwtFromRequest(request); // Request에서 jwt key 추출

        if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
            try {
                String loginId = jwtProvider.getLoginId(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginId); // CustomUserdetails 사용

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // SecurityContextHolder에 저장 → 인증 성공 처리
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JwtException e) {
                logger.error("JWT validation failed", e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}