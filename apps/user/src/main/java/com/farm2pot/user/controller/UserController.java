package com.farm2pot.user.controller;

import com.farm2pot.common.exception.UserException;
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

    // 사용자 정보 확인 - loginId
    @GetMapping("/userinfo/{loginId}")
    public ResponseMessage<User> getUserInfoByLoginId(@PathVariable String loginId) {
        return ResponseMessage.success("select User by LoginId Info", userService.findByLoginId(loginId));
    }

    // 사용자 정보 확인 - Id
//    @GetMapping("/userinfo/{id}")
//    public ResponseMessage<User> getUserInfoByUserId(@PathVariable Long id) {
//        return ResponseMessage.success("select User by Id Info", userService.findById(id));
//    }

    @PostMapping("/check-password")
    public ResponseMessage<User> checkPassword(@RequestBody UserDto request) {
        try {
            userService.checkUser(request);
            return ResponseMessage.success("checking user.... success", userService.findById(request.getId()));
        }catch (UserException e) {
            return ResponseMessage.success(e.getMessage(), null);
        }


    }
}
