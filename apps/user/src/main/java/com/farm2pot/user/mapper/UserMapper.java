package com.farm2pot.user.mapper;

import com.farm2pot.common.config.MapStructConfig;
import com.farm2pot.common.mapper.BaseMapper;
import com.farm2pot.user.controller.dto.UserDto;
import com.farm2pot.user.entity.User;
import org.mapstruct.AfterMapping;
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
public interface UserMapper extends BaseMapper<User, UserDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateEntityFromDto(UserDto dto, @MappingTarget User entity);

    @AfterMapping
    default void afterUpdate(UserDto dto, @MappingTarget User entity) {
        if (dto.getRoles() != null) {
            entity.getRoles().clear();
            entity.getRoles().addAll(dto.getRoles());
        }
    }
}
