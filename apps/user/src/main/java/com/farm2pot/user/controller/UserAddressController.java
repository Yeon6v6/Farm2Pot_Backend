package com.farm2pot.user.controller;

import com.farm2pot.common.response.ResponseMessage;
import com.farm2pot.user.entity.UserAddress;
import com.farm2pot.user.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.farm2pot.user.controller
 * author         : TAEJIN
 * date           : 2025-10-31
 * description    :
 */

@RestController
@RequestMapping("/api/addr")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    @GetMapping("/addrs")
    public ResponseMessage<UserAddress> findUserAddressAll(@PathVariable Long id) {
        return ResponseMessage.success("",userAddressService.findUserAddressById(id));
    }

}
