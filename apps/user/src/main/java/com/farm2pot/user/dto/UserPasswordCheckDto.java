package com.farm2pot.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPasswordCheckDto {
    private Long id;
    private String password;
    private String loginId;
    private String oldPassword;

}
