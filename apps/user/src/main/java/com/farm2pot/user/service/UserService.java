package com.farm2pot.user.service;

import com.farm2pot.common.exception.UserErrorCode;
import com.farm2pot.common.exception.UserException;
import com.farm2pot.security.service.JwtProvider;
import com.farm2pot.user.dto.UserDto;
import com.farm2pot.user.entity.User;
import com.farm2pot.auth.mapper.RefreshTokenMapper;
import com.farm2pot.user.mapper.UserMapper;
import com.farm2pot.auth.repository.RefreshTokenRepository;
import com.farm2pot.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.farm2pot.common.exception.UserErrorCode.INVALID_PASASWORD;
import static com.farm2pot.common.exception.UserErrorCode.USER_NOT_FOUND;

/**
 * packageName    : com.farm2pot.user.service
 * author         : TAEJIN
 * date           : 2025-10-13
 * description    :
 */

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenMapper refreshTokenMapper;
    private final UserMapper userMapper;



    /**
     * 로그아웃 (Refresh Token 제거)
     */
    @Transactional
    public void logout(String loginId) {
        if (loginId == null || loginId.isBlank()) {
            throw new UserException(USER_NOT_FOUND);
        }
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
        refreshTokenRepository.deleteByUserId(user.getId());
    }

    /**
     * 사용자 정보 조회 (loginId)
     */
    public User findByLoginId(String loginId) {
        if (loginId == null || loginId.isBlank()) {
            throw new UserException(USER_NOT_FOUND);
        }
        return userRepository.findByLoginId(loginId).orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }
    /**
     * 사용자 정보 조회 (id)
     */
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }

    /**
     * 사용자 정보 수정 (프로필 수정)
     */
    @Transactional
    public User editUserInfo(UserDto userDto) {
        User user = userRepository.findByLoginId(userDto.getLoginId())
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
        String password = encodePassword(userDto.getPassword());
        userDto.setPassword(password);
        userMapper.updateEntityFromDto(
                userDto, user);
        return user;
    }


    /**
     * 패스워드 체크
     */
    public boolean validatePassword(String oldPassword, String newPassword ) {
        return Optional.of(passwordEncoder.matches(newPassword, oldPassword))
                .filter(result -> result) // true일 때만 통과
                .orElseThrow(() -> new UserException(INVALID_PASASWORD));
    }

    public boolean checkUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserException(UserErrorCode.UNAUTHORIZED_USER));
        return validatePassword(user.getPassword(), userDto.getPassword());
    }



    public String encodePassword (String password) {
        return passwordEncoder.encode(password);
    }
}

