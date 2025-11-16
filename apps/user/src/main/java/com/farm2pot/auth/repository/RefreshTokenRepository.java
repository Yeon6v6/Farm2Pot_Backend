package com.farm2pot.auth.repository;

import com.farm2pot.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : com.farm2pot.auth.repository
 * author         : TAEJIN
 * date           : 2025-10-03
 * description    :
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUserId(Long userId);
}
