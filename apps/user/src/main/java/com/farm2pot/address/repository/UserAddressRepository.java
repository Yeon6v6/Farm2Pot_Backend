package com.farm2pot.address.repository;

import com.farm2pot.address.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.farm2pot.auth
 * author         : TAEJIN
 * date           : 2025-10-03
 * description    :
 */

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    Optional<UserAddress> findById(Long Id);
    Optional<List<UserAddress>> findAllAddressByUserId(Long userId);
    Optional<String> deleteByUserId(Long userId);
}

