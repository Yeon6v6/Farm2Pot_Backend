package com.farm2pot.user.controller;

import com.farm2pot.common.response.ResponseMessage;
import com.farm2pot.user.dto.UserDto;
import com.farm2pot.user.entity.User;
import com.farm2pot.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/health")
    public ResponseMessage health() {
        return ResponseMessage.success(Map.of(
                    "status", "UP",
                    "service", "user-service",
                    "timestamp", System.currentTimeMillis()
                )
        );
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseMessage<String> logout(@RequestParam("loginId") String loginId ) {
        userService.logout(loginId);
        return ResponseMessage.success("logout success", "");
    }

    // 사용자 정보 수정
    @PostMapping("/edit")
    public ResponseMessage<User> editUser(@RequestBody @Validated UserDto request) {
        return ResponseMessage.success("editUser success", userService.editUserInfo(request));
    }

    @GetMapping("/userinfo/{loginId}")
    public ResponseMessage<User> getUserInfo(@RequestParam @Validated UserDto request) {
        return ResponseMessage.success("select User Info", userService.findByLoginId(request.getLoginId()));
    }
}
