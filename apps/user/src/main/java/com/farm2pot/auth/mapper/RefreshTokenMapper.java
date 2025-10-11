package com.farm2pot.auth.mapper;

import com.farm2pot.auth.dto.RefreshTokenRequest;
import com.farm2pot.auth.entity.RefreshToken;
import com.farm2pot.common.config.MapStructConfig;
import com.farm2pot.common.mapper.BaseMapper;
import org.mapstruct.Mapper;

/**
 * packageName    : com.farm2pot.auth.refreshToken.mapper
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    :
 */
@Mapper(config = MapStructConfig.class)
//@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface RefreshTokenMapper extends BaseMapper<RefreshToken, RefreshTokenRequest> {

//    RefreshTokenMapper INSTANCE = Mappers.getMapper(RefreshTokenMapper.class);

//    @Override
//    RefreshTokenDTO toDto(RefreshToken entity);

//    @Override
//    @Mapping(target = "id", ignore = true) // ID는 DB에서 자동 생성
//    RefreshToken toEntity(RefreshTokenDTO dto);
}
