package com.farm2pot.auth.controller;

import com.farm2pot.auth.controller.dto.TokenRefresh;
import com.farm2pot.auth.controller.dto.CreateUser;
import com.farm2pot.auth.service.AuthService;
import com.farm2pot.common.response.ResponseMessage;
import com.farm2pot.user.controller.dto.UserDto;
import com.farm2pot.auth.service.dto.UserLoginTokenResponse;
import com.farm2pot.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseMessage<UserLoginTokenResponse> refresh( @RequestBody TokenRefresh request) {
        return ResponseMessage.success("token refresh ..... success", authService.refresh(request));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseMessage<UserLoginTokenResponse> login(
            @RequestBody @Validated UserDto request, HttpServletResponse response
    ) {

        UserLoginTokenResponse loginResponse = authService.login(request);
        response.setHeader("X-USER-ID", loginResponse.getUserId().toString());
        return ResponseMessage.success("login success", loginResponse);
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseMessage<String> register(
            @RequestBody @Validated CreateUser request // TODO : -> 회원가입용 requestDTO 추가해야 함.
    ) {
        authService.register(request); // 실제 회원가입 처리
        return ResponseMessage.success("join success", "");
    }

    @GetMapping("/users")
    public ResponseMessage<List<User>> getAllUsers() {
        return ResponseMessage.success("select All Users" , authService.getAllUsers());
    }

}