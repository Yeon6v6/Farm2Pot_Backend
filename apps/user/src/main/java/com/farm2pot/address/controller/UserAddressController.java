package com.farm2pot.address.controller;

import com.farm2pot.common.response.ResponseMessage;
import com.farm2pot.address.service.dto.UserAddressDto;
import com.farm2pot.address.entity.UserAddress;
import com.farm2pot.address.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.farm2pot.user.controller
 * author         : TAEJIN
 * date           : 2025-10-31
 * description    :
 */

@RestController
@RequestMapping("/api/user/addr")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    /**
     * 사용자의 UserId로 사용자의 배송지 목록 조회
     * @param userId
     * @return
     */
    @GetMapping("/address/{userId}")
    public ResponseMessage<List<UserAddress>> findAllAddress(@PathVariable("userId") Long userId) {
        return ResponseMessage.success("Select UserAddress By UserId",userAddressService.findAllAddressByUserId(userId));
    }


    /**
     * 사용자 배송지 추가
     * @param userAddressDto
     * @return
     */
    @PostMapping("/address")
    public ResponseMessage<String> addUserAddress(@RequestBody UserAddressDto userAddressDto) {
        try{
            userAddressService.addUserAddress(userAddressDto);
            return ResponseMessage.success("Insert UserAddress.... Success");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.fail("Insert UserAddress.... Failure");
        }
    }

    /**
     * 사용자의 배송지 목록 중 특정 배송지 하나를 수정
     * @param addrId
     * @param userAddressDto
     * @return
     */
    @PutMapping("/address/{addrId}")
    public ResponseMessage<UserAddress> editAddress(@PathVariable("addrId") Long addrId, @RequestBody UserAddressDto userAddressDto) {
        return ResponseMessage.success("Select UserAddress By UserId",userAddressService.findUserAddressById(addrId));
    }

    /**
     * 사용자의 배송지 목록 중 특정 배송지 하나를 삭제
     * @param addrId
     * @return
     */
    @DeleteMapping("/address/delete/uid/{addrId}")
    public ResponseMessage<String> deleteAddress(@PathVariable("addrId") Long addrId) {
        userAddressService.deleteUserAddressByUserId(addrId);
        return ResponseMessage.success("Delete UserAddress By AddressId");
    }
}
