package com.farm2pot.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * packageName    : com.farm2pot.common.service
 * author         : TAEJIN
 * date           : 2025-10-14
 * description    :
 */
@Service
@RequiredArgsConstructor
public class CoreService {

    private final PasswordEncoder passwordEncoder;


    /**
     * UUID 생성
     * @param prefix
     * @return
     */
    public String generateUUID(String prefix) {
        UUID uuid = UUID.randomUUID();
        return prefix.concat(uuid.toString().replace("-", "").toUpperCase());
    }
    public String encode(String value) {
        return passwordEncoder.encode(value);
    }
}
