package com.farm2pot.address.service;

import com.farm2pot.common.exception.UserErrorCode;
import com.farm2pot.common.exception.UserException;
import com.farm2pot.address.service.dto.UserAddressDto;
import com.farm2pot.user.entity.User;
import com.farm2pot.address.entity.UserAddress;
import com.farm2pot.address.mapper.UserAddressMapper;
import com.farm2pot.address.repository.UserAddressRepository;
import com.farm2pot.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private final UserRepository userRepository;
    private final UserAddressMapper userAddressMapper;

    /**
     * USERADDRESS pk로 배송지 찾기
     * @param id
     * @return
     */
    public UserAddress findUserAddressById(Long id) {
        return userAddressRepository.findById(id).orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND_ADDRESS));
    }

    /**
     * 사용자 ID(pk)로 배송지 목록 찾기
     * @param userId
     * @return
     */
    public List<UserAddress> findAllAddressByUserId(Long userId) {
        return userAddressRepository.findAllAddressByUserId(userId).orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND_ADDRESS));
    }


    /**
     * 사용자 배송지 추가
     * @param userAddressDto
     */
    public void addUserAddress(UserAddressDto userAddressDto) {
        Long userId = userAddressDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND)
        );
        //DTO에 UserEntity 세팅
        userAddressDto.setUser(user);
        //UserAddress Insert
        userAddressRepository.save(userAddressMapper.toEntity(userAddressDto));

    }

    /**
     * 사용자ID로 사용자의 배송지 모두 제거
     * @param UserId
     */
    public void deleteUserAddressByUserId(Long UserId) {
        userAddressRepository.deleteByUserId(UserId);
    }
}
