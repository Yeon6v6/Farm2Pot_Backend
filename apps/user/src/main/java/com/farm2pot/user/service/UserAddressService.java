package com.farm2pot.user.service;

import com.farm2pot.common.exception.UserErrorCode;
import com.farm2pot.common.exception.UserException;
import com.farm2pot.user.entity.UserAddress;
import com.farm2pot.user.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.farm2pot.user.service
 * author         : TAEJIN
 * date           : 2025-10-31
 * description    :
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;

    /**
     * pk로 배송지 찾기
     * @param id
     * @return
     */
    public UserAddress findUserAddressById(Long id) {
        return userAddressRepository.findById(id).orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND_ADDRESS));
    }
    /**
     * pk로 배송지 찾기
     * @param userId
     * @return
     */
    public UserAddress findUserAddressByUserId(Long userId) {
        return userAddressRepository.findByUserId(userId).orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND_ADDRESS));
    }
}
