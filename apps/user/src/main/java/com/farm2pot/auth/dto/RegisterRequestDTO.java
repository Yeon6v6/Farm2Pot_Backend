package com.farm2pot.auth.dto;

import com.farm2pot.user.dto.UserAddressDto;
import com.farm2pot.user.dto.UserDto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO extends UserDto {

    private Long id;
    @NotBlank(message = "로그인 아이디는 필수입니다.")
    private String loginId;
    @Size(min = 1, message = "비밀번호는 최소 8자리 이상이어야 합니다.")
    private String password;
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;
    private String name;
    private String loginType = "LOCAL";
    private String phoneNo;
    private Date birthDay;
    private int status = 1;
    private String gender;
    private String nickName;
    private List<String> roles = List.of("ROLE_USER");
    private UserAddressDto userAddressDto; // 사용자 주소

}
