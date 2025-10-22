package com.farm2pot.auth.mapper;

import com.farm2pot.auth.dto.RefreshTokenDto;
import com.farm2pot.auth.entity.RefreshToken;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-22T11:00:16+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class RefreshTokenMapperImpl implements RefreshTokenMapper {

    @Override
    public RefreshToken toEntity(RefreshTokenDto dto) {
        if ( dto == null ) {
            return null;
        }

        RefreshToken.RefreshTokenBuilder refreshToken = RefreshToken.builder();

        if ( dto.getToken() != null ) {
            refreshToken.token( dto.getToken() );
        }
        if ( dto.getUserId() != null ) {
            refreshToken.userId( dto.getUserId() );
        }
        if ( dto.getExpiryDate() != null ) {
            refreshToken.expiryDate( dto.getExpiryDate() );
        }

        return refreshToken.build();
    }

    @Override
    public RefreshTokenDto toDto(RefreshToken entity) {
        if ( entity == null ) {
            return null;
        }

        RefreshTokenDto.RefreshTokenDtoBuilder refreshTokenDto = RefreshTokenDto.builder();

        if ( entity.getId() != null ) {
            refreshTokenDto.id( entity.getId() );
        }
        if ( entity.getToken() != null ) {
            refreshTokenDto.token( entity.getToken() );
        }
        if ( entity.getUserId() != null ) {
            refreshTokenDto.userId( entity.getUserId() );
        }
        if ( entity.getExpiryDate() != null ) {
            refreshTokenDto.expiryDate( entity.getExpiryDate() );
        }

        return refreshTokenDto.build();
    }

    @Override
    public void updateEntityFromDto(RefreshTokenDto dto, RefreshToken entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getToken() != null ) {
            entity.setToken( dto.getToken() );
        }
        if ( dto.getUserId() != null ) {
            entity.setUserId( dto.getUserId() );
        }
        if ( dto.getExpiryDate() != null ) {
            entity.setExpiryDate( dto.getExpiryDate() );
        }
    }
}
