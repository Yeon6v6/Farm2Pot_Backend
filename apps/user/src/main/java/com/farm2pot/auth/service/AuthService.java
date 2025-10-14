package com.farm2pot.auth.service;


import com.farm2pot.auth.dto.RefreshTokenDto;
import com.farm2pot.user.dto.*;
import com.farm2pot.auth.entity.RefreshToken;
import com.farm2pot.user.entity.User;
import com.farm2pot.auth.mapper.RefreshTokenMapper;
import com.farm2pot.user.mapper.UserMapper;
import com.farm2pot.auth.repository.RefreshTokenRepository;
import com.farm2pot.user.repository.UserRepository;
import com.farm2pot.common.exception.UserException;
import com.farm2pot.common.exception.UserErrorCode;
import com.farm2pot.security.service.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static com.farm2pot.common.exception.UserErrorCode.USER_NOT_FOUND;

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
     * Refresh Token을 이용한 Access Token 재발급
     */
    public UserLoginTokenResponse refresh(RefreshTokenDto request) {
        // 1. 토큰 존재 확인
        RefreshToken tokenEntity = refreshTokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new UserException(UserErrorCode.INVALID_TOKEN));

        // 2. 만료 여부 확인
        if (tokenEntity.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(tokenEntity);
            throw new UserException(UserErrorCode.EXPIRED_TOKEN);
        }

        // 3. 사용자 정보 확인
        User user = userRepository.findByLoginId(tokenEntity.getLoginId())
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        // 4. 새 Access Token 발급
        String newAccessToken = jwtProvider.generateAccessToken(user.getLoginId(), user.getRoles());

        return new UserLoginTokenResponse(newAccessToken, request.getToken(), userMapper.toDto(user));
    }

    /**
     * 로그인 처리
     */
    public UserLoginTokenResponse login(UserDto request) {
        // 1. 로그인 ID 확인
        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        // 2. 비밀번호 확인
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID_CREDENTIALS);
        }

        // 3. Access Token & Refresh Token 발급
        String accessToken = jwtProvider.generateAccessToken(user.getLoginId(), user.getRoles());
        String refreshToken = jwtProvider.generateRefreshToken(user.getLoginId());

        // 4. Refresh Token 저장
        RefreshTokenDto dto = RefreshTokenDto.builder()
                .token(refreshToken)
                .loginId(user.getLoginId())
                .expiryDate(Instant.now().plusMillis(604800000)) // 7일
                .build();

        refreshTokenRepository.save(refreshTokenMapper.toEntity(dto));

        // 5. 결과 반환
        return new UserLoginTokenResponse(accessToken, refreshToken, userMapper.toDto(user));
    }

    /**
     * 회원가입
     */
    public void register(UserDto userDto){
        User user = userMapper.toEntity(userDto);
    }

    public void init() {
        UserDto dto = UserDto.builder()
                .loginId("xclick")
                .email("taejin1@example.com")
                .password(passwordEncoder.encode("a"))
                .name("김태진")
                .loginType("LOCAL")
                .phoneNo("010-1234-5678")
                .status(1)
                .gender("M")
                .nickName("농부태진")
                .roles(List.of("ROLE_USER","ROLE_ADMIN"))
                .build();

        User entity = userMapper.toEntity(dto);
        userRepository.save(entity);
    }
//    @PostConstruct
    public void initInsertData() {
        init();
    }
}