package com.farm2pot.auth.dto;

import lombok.Builder;

/**
 * packageName    : com.farm2pot.auth.dto
 * author         : TAEJIN
 * date           : 2025-10-03
 * description    :
 */

@Builder
public record UserLoginTokenResponse(String accessToken, String refreshToken) {}
