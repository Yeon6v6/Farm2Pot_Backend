package com.farm2pot.user.dto;

import lombok.*;

/**
 * packageName    : com.farm2pot.auth.dto
 * author         : TAEJIN
 * date           : 2025-10-03
 * description    :
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginTokenResponse {
    private String accessToken;
    private String refreshToken;
    private UserDto userDto;
}
