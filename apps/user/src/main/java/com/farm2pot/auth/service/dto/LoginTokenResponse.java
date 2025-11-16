package com.farm2pot.auth.service.dto;

import com.farm2pot.user.controller.dto.UserDto;
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
public class LoginTokenResponse {
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private UserDto userDto;
}
