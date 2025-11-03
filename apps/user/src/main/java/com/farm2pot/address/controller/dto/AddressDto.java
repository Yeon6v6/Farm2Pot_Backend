package com.farm2pot.address.controller.dto;

import com.farm2pot.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : com.farm2pot.user.dto
 * author         : TAEJIN
 * date           : 2025-10-14
 * description    :
 */


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private Long userId;
    private User user;
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
