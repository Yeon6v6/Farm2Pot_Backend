package com.farm2pot.address.mapper;

import com.farm2pot.common.config.MapStructConfig;
import com.farm2pot.common.mapper.BaseMapper;
import com.farm2pot.address.service.dto.UserAddressDto;
import com.farm2pot.address.entity.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * packageName    : com.farm2pot.auth.mapper
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    :
 */
@Mapper(config = MapStructConfig.class)
public interface UserAddressMapper extends BaseMapper<UserAddress, UserAddressDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UserAddressDto dto, @MappingTarget UserAddress entity);
}
