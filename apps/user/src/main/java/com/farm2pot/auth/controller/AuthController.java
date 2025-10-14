package com.farm2pot.auth.controller;

import com.farm2pot.auth.dto.RefreshTokenDto;
import com.farm2pot.auth.service.AuthService;
import com.farm2pot.common.response.ResponseMessage;
import com.farm2pot.user.dto.UserDto;
import com.farm2pot.user.dto.UserLoginTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.farm2pot.auth.controller
 * author         : TAEJIN
 * date           : 2025-10-03
 * description    :
 */
@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 토큰 재발급
    @PostMapping("/refresh")
    public ResponseMessage<UserLoginTokenResponse> refresh( @RequestBody RefreshTokenDto request) {
        return ResponseMessage.success("token refreshed", authService.refresh(request));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseMessage<UserLoginTokenResponse> login(
            @RequestBody @Validated UserDto request
    ) {
        return ResponseMessage.success("login success", authService.login(request));
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseMessage<String> register(
            @RequestBody @Validated UserDto request
    ) {
        authService.register(request); // 실제 회원가입 처리
        return ResponseMessage.success("join success", null);
    }


}