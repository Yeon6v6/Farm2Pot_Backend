package com.farm2pot.auth.service;


import com.farm2pot.auth.dto.*;
import com.farm2pot.auth.entity.RefreshToken;
import com.farm2pot.auth.entity.User;
import com.farm2pot.auth.mapper.RefreshTokenMapper;
import com.farm2pot.auth.mapper.UserMapper;
import com.farm2pot.auth.repository.RefreshTokenRepository;
import com.farm2pot.auth.repository.UserRepository;
import com.farm2pot.common.exception.UserException;
import com.farm2pot.common.exception.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * packageName    : com.farm2pot.auth.service
 * author         : TAEJIN
 * date           : 2025-10-03
 * description    :
 */

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenMapper refreshTokenMapper;
    private final UserMapper userMapper;

    /**
     * 로그인 처리
     */
    public UserLoginTokenResponse login(UserLoginRequest request) {
        // 1. 로그인 ID 확인
        User user = userRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        // 2. 비밀번호 확인
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID_CREDENTIALS);
        }

        // 3. Access Token & Refresh Token 발급
        String accessToken = jwtProvider.generateAccessToken(user.getLoginId(), user.getRoles());
        String refreshToken = jwtProvider.generateRefreshToken(user.getLoginId());

        // 4. Refresh Token 저장
        RefreshTokenRequest dto = RefreshTokenRequest.builder()
                .token(refreshToken)
                .loginId(user.getLoginId())
                .expiryDate(Instant.now().plusMillis(604800000)) // 7일
                .build();

        refreshTokenRepository.save(refreshTokenMapper.toEntity(dto));

        // 5. 결과 반환
        return new UserLoginTokenResponse(accessToken, refreshToken);
    }

    /**
     * Refresh Token을 이용한 Access Token 재발급
     */
    public UserLoginTokenResponse refresh(String refreshToken) {
        // 1. 토큰 존재 확인
        RefreshToken tokenEntity = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new UserException(UserErrorCode.INVALID_TOKEN));

        // 2. 만료 여부 확인
        if (tokenEntity.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(tokenEntity);
            throw new UserException(UserErrorCode.EXPIRED_TOKEN);
        }

        // 3. 사용자 정보 확인
        User user = userRepository.findByLoginId(tokenEntity.getLoginId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        // 4. 새 Access Token 발급
        String newAccessToken = jwtProvider.generateAccessToken(user.getLoginId(), user.getRoles());

        return new UserLoginTokenResponse(newAccessToken, refreshToken);
    }

    /**
     * 로그아웃 (Refresh Token 제거)
     */
    public void logout(String loginId) {
        if (loginId == null || loginId.isBlank()) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }
        refreshTokenRepository.deleteByLoginId(loginId);
    }

    /**
     * 회원가입
     */
    public void register(UserJoinRequest userDto){
        User user = userMapper.toEntity(userDto);
    }

    public void init() {
        UserJoinRequest dto = UserJoinRequest.builder()
                .loginId("taejin123")
                .email("taejin1@example.com")
                .password(passwordEncoder.encode("a"))
                .name("김태진")
                .loginType("")
                .phoneNo("010-1234-5678")
                .status(1)
                .gender("M")
                .nickName("농부태진")
                .build();

        User entity = userMapper.toEntity(dto);
        userRepository.save(entity);
    }
    //@PostConstruct
    public void initInsertData() {
        init();
    }
}