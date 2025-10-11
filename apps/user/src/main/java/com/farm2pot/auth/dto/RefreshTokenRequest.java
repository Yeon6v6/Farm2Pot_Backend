package com.farm2pot.auth.dto;

import lombok.Builder;

import java.time.Instant;

/**
 * packageName    : com.farm2pot.auth.refreshToken.dto
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    :
 */

@Builder
public record RefreshTokenRequest(
        Long id,
        String token,
        String loginId,
        Instant expiryDate
) {}
