package com.farm2pot.user.service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordCheckDto {
    private Long id;
    private String password;   // request에 있는 password
    private String loginId;
    private String oldPassword; // DB에서 찾을 password

}
