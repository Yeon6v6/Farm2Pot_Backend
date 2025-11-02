package com.farm2pot.auth.controller.dto;

import lombok.*;

import java.time.Instant;

/**
 * packageName    : com.farm2pot.user.dto
 * author         : TAEJIN
 * date           : 2025-10-13
 * description    :
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefresh {
    private Long id;
    private String token;
    private Long userId;
    private Instant expiryDate;
}
