package com.farm2pot.user.dto;

import java.time.LocalDateTime;

/**
 * packageName    : com.farm2pot.user.dto
 * author         : TAEJIN
 * date           : 2025-10-14
 * description    :
 */
public class UserAddressDto {
    private Long id;

    // User 엔티티의 id만 포함 (원하면 username, email 등 추가 가능)
    private String userId;
    private String loginId;

    private String recipientName;
    private String phoneNumber;
    private String postalCode;
    private String addressLine1;
    private String addressLine2;
    private boolean isDefault;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
