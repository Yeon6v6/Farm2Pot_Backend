package com.farm2pot.user.repository;

import com.farm2pot.user.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : com.farm2pot.auth
 * author         : TAEJIN
 * date           : 2025-10-03
 * description    :
 */

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    Optional<UserAddress> findById(Long Id);
}

