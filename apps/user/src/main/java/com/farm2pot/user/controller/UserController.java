package com.farm2pot.user.controller;

import com.farm2pot.common.exception.UserException;
import com.farm2pot.common.response.ResponseMessage;
import com.farm2pot.user.controller.dto.UserDto;
import com.farm2pot.user.service.dto.UserPasswordCheckDto;
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
    public ResponseMessage<String> logout(@RequestParam("id") Long id ) {
        userService.logout(id);
        return ResponseMessage.success("logout success");
    }

    /**
     * 사용자 정보 수정
     * @param userDto
     * @return
     */
    @PatchMapping("/edit")
    public ResponseMessage<User> editUser(@RequestBody @Validated UserDto userDto) {
        return ResponseMessage.success("edit UserInfo ...success", userService.editUserInfo(userDto));
    }

    /**
     * 사용자 삭제 - id
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseMessage<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseMessage.success("delete User ...success");
    }

    /**
     * 사용자 정보 확인 - loginId
     * @param loginId
     * @return
     */
    @GetMapping("/userinfo-lid/{loginId}")
    public ResponseMessage<User> getUserInfoByLoginId(@PathVariable("loginId") String loginId) {
        return ResponseMessage.success("select User by LoginId Info", userService.findByLoginId(loginId));
    }

    /**
     * 사용자 정보 확인 - id
     * @param id
     * @return
     */
    @GetMapping("/userinfo-uid/{id}")
    public ResponseMessage<User> getUserInfoByUserId(@PathVariable("id") Long id) {
        return ResponseMessage.success("select User by Id Info", userService.findById(id));
    }

    /**
     * 사용자 패스워드 체크.. 마이페이지 접근 시 필요
     * X-USER-ID, 변경패스워드 필요
     * @param userPasswordCheckDto
     * @return
     */
    @PostMapping("/check-password")
    public ResponseMessage<String> checkPassword(@RequestBody UserPasswordCheckDto userPasswordCheckDto) {
        try {
            userService.checkUser(userPasswordCheckDto);
            return ResponseMessage.success("CHECK PASSWORD...... SUCCESS");
        }catch (UserException e) {
            return ResponseMessage.fail(e.getMessage());
        }
    }
}
