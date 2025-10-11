package com.farm2pot.auth.controller;

import com.farm2pot.auth.dto.UserJoinRequest;
import com.farm2pot.auth.dto.UserLoginRequest;
import com.farm2pot.auth.dto.UserLoginTokenResponse;
import com.farm2pot.auth.service.AuthService;
import com.farm2pot.common.response.ResponseMessage;
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

    // 로그인
    @PostMapping("/login")
    public ResponseMessage<UserLoginTokenResponse> login(
            @RequestBody @Validated UserLoginRequest request
    ) {
        return ResponseMessage.success("login success", authService.login(request));
    }

    // 토큰 재발급
    @GetMapping("/refresh")
    public ResponseMessage<UserLoginTokenResponse> refresh(
            @RequestParam String refreshToken
    ) {
        return ResponseMessage.success("token refreshed", authService.refresh(refreshToken));
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseMessage<String> register(
            @RequestBody @Validated UserJoinRequest request
    ) {
        authService.register(request); // 실제 회원가입 처리
        return ResponseMessage.success("join success", null);
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseMessage<String> logout(
            @RequestParam("loginId") String loginId
    ) {
        authService.logout(loginId);
        return ResponseMessage.success("logout success", null);
    }
}