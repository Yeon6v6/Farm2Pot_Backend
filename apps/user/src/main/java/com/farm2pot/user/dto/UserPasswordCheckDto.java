package com.farm2pot.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordCheckDto {
    private Long id;
    private String password;
    private String loginId;
    private String oldPassword;

}
