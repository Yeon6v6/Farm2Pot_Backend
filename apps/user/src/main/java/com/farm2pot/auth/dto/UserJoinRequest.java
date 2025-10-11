package com.farm2pot.auth.dto;

import lombok.Builder;

import java.util.Date;
import java.util.List;

/**
 * packageName    : com.farm2pot.auth.dto
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    : 사용자 정보 DTO
 */

@Builder
public record UserJoinRequest(
        Long id,
        String loginId,
        String email,
        String password,
        String name,
        String loginType,
        String phoneNo,
        Date birthDay,
        int status,
        String gender,
        String nickName,
        List<String> roles
) {}
