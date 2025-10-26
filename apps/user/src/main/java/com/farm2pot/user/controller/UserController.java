package com.farm2pot.user.controller;

import com.farm2pot.common.exception.UserException;
import com.farm2pot.common.response.ResponseMessage;
import com.farm2pot.user.dto.UserDto;
import com.farm2pot.user.dto.UserPasswordCheckDto;
import com.farm2pot.user.entity.User;
import com.farm2pot.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseMessage<String> logout(@RequestParam("id") Long id ) {
        userService.logout(id);
        return ResponseMessage.success("logout success", "");
    }

    // 사용자 정보 수정
    @PostMapping("/edit")
    public ResponseMessage<User> editUser(@RequestBody @Validated UserDto request) {
        return ResponseMessage.success("editUser success", userService.editUserInfo(request));
    }

    // 사용자 정보 확인 - loginId
    @GetMapping("/userinfo-lid/{loginId}")
    public ResponseMessage<User> getUserInfoByLoginId(@PathVariable String loginId) {
        return ResponseMessage.success("select User by LoginId Info", userService.findByLoginId(loginId));
    }

    // 사용자 정보 확인 - Id
    @GetMapping("/userinfo-id/{id}")
    public ResponseMessage<User> getUserInfoByUserId(@PathVariable Long id) {
        return ResponseMessage.success("select User by Id Info", userService.findById(id));
    }

    /**
     * 사용자 패스워드 체크.. 마이페이지 접근 시 필요
     * @param userPasswordCheckDto
     * @return
     */
    @PostMapping("/check-password")
    public ResponseMessage<String> checkPassword(@RequestBody UserPasswordCheckDto userPasswordCheckDto) {
        try {
            boolean userValidate = userService.checkUser(userPasswordCheckDto);
            return userValidate? ResponseMessage.success("checking user.... success") : ResponseMessage.fail("checking user.... success");
        }catch (UserException e) {
            return ResponseMessage.success(e.getMessage());
        }
    }
}
