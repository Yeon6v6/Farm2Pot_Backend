package com.farm2pot.auth.mapper;

import com.farm2pot.auth.dto.RefreshTokenDto;
import com.farm2pot.auth.entity.RefreshToken;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-14T19:04:12+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class RefreshTokenMapperImpl implements RefreshTokenMapper {

    @Override
    public RefreshToken toEntity(RefreshTokenDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        RefreshToken.RefreshTokenBuilder refreshToken = RefreshToken.builder();

        if ( arg0.getToken() != null ) {
            refreshToken.token( arg0.getToken() );
        }
        if ( arg0.getLoginId() != null ) {
            refreshToken.loginId( arg0.getLoginId() );
        }
        if ( arg0.getExpiryDate() != null ) {
            refreshToken.expiryDate( arg0.getExpiryDate() );
        }

        return refreshToken.build();
    }

    @Override
    public RefreshTokenDto toDto(RefreshToken arg0) {
        if ( arg0 == null ) {
            return null;
        }

        RefreshTokenDto.RefreshTokenDtoBuilder refreshTokenDto = RefreshTokenDto.builder();

        if ( arg0.getId() != null ) {
            refreshTokenDto.id( arg0.getId() );
        }
        if ( arg0.getToken() != null ) {
            refreshTokenDto.token( arg0.getToken() );
        }
        if ( arg0.getLoginId() != null ) {
            refreshTokenDto.loginId( arg0.getLoginId() );
        }
        if ( arg0.getExpiryDate() != null ) {
            refreshTokenDto.expiryDate( arg0.getExpiryDate() );
        }

        return refreshTokenDto.build();
    }

    @Override
    public void updateEntityFromDto(RefreshTokenDto arg0, RefreshToken arg1) {
        if ( arg0 == null ) {
            return;
        }

        if ( arg0.getId() != null ) {
            arg1.setId( arg0.getId() );
        }
        if ( arg0.getToken() != null ) {
            arg1.setToken( arg0.getToken() );
        }
        if ( arg0.getLoginId() != null ) {
            arg1.setLoginId( arg0.getLoginId() );
        }
        if ( arg0.getExpiryDate() != null ) {
            arg1.setExpiryDate( arg0.getExpiryDate() );
        }
    }
}
