package com.farm2pot.auth.mapper;

import com.farm2pot.auth.controller.dto.TokenRefresh;
import com.farm2pot.auth.entity.RefreshToken;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-03T19:12:27+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class RefreshTokenMapperImpl implements RefreshTokenMapper {

    @Override
    public RefreshToken toEntity(TokenRefresh dto) {
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
    public TokenRefresh toDto(RefreshToken entity) {
        if ( entity == null ) {
            return null;
        }

        TokenRefresh.TokenRefreshBuilder tokenRefresh = TokenRefresh.builder();

        if ( entity.getId() != null ) {
            tokenRefresh.id( entity.getId() );
        }
        if ( entity.getToken() != null ) {
            tokenRefresh.token( entity.getToken() );
        }
        if ( entity.getUserId() != null ) {
            tokenRefresh.userId( entity.getUserId() );
        }
        if ( entity.getExpiryDate() != null ) {
            tokenRefresh.expiryDate( entity.getExpiryDate() );
        }

        return tokenRefresh.build();
    }

    @Override
    public void updateEntityFromDto(TokenRefresh dto, RefreshToken entity) {
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
