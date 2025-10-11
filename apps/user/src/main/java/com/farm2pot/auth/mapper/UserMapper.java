package com.farm2pot.auth.mapper;

import com.farm2pot.auth.dto.UserJoinRequest;
import com.farm2pot.auth.entity.User;
import com.farm2pot.common.config.MapStructConfig;
import com.farm2pot.common.mapper.BaseMapper;
import org.mapstruct.Mapper;

/**
 * packageName    : com.farm2pot.auth.mapper
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    :
 */
//@Mapper( builder = @Builder(disableBuilder = false))
@Mapper(config = MapStructConfig.class)
public interface UserMapper extends BaseMapper<User, UserJoinRequest> {
//    @Override
//    @Mapping(target = "id", ignore = true) // DB auto increment
//    User toEntity(UserDTO dto);
//
//    @Override
//    UserDTO toDto(User entity);
}
