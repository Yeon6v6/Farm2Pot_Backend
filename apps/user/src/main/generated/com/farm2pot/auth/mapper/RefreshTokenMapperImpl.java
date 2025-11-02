package com.farm2pot.auth.mapper;

import com.farm2pot.auth.controller.dto.TokenRefresh;
import com.farm2pot.auth.entity.RefreshToken;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-01T19:53:57+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class RefreshTokenMapperImpl implements RefreshTokenMapper {

    @Override
    public RefreshToken toEntity(TokenRefresh arg0) {
        if ( arg0 == null ) {
            return null;
        }

        RefreshToken.RefreshTokenBuilder refreshToken = RefreshToken.builder();

        if ( arg0.getToken() != null ) {
            refreshToken.token( arg0.getToken() );
        }
        if ( arg0.getUserId() != null ) {
            refreshToken.userId( arg0.getUserId() );
        }
        if ( arg0.getExpiryDate() != null ) {
            refreshToken.expiryDate( arg0.getExpiryDate() );
        }

        return refreshToken.build();
    }

    @Override
    public TokenRefresh toDto(RefreshToken arg0) {
        if ( arg0 == null ) {
            return null;
        }

        TokenRefresh.TokenRefreshBuilder token = TokenRefresh.builder();

        if ( arg0.getId() != null ) {
            token.id( arg0.getId() );
        }
        if ( arg0.getToken() != null ) {
            token.token( arg0.getToken() );
        }
        if ( arg0.getUserId() != null ) {
            token.userId( arg0.getUserId() );
        }
        if ( arg0.getExpiryDate() != null ) {
            token.expiryDate( arg0.getExpiryDate() );
        }

        return token.build();
    }

    @Override
    public void updateEntityFromDto(TokenRefresh arg0, RefreshToken arg1) {
        if ( arg0 == null ) {
            return;
        }

        if ( arg0.getId() != null ) {
            arg1.setId( arg0.getId() );
        }
        if ( arg0.getToken() != null ) {
            arg1.setToken( arg0.getToken() );
        }
        if ( arg0.getUserId() != null ) {
            arg1.setUserId( arg0.getUserId() );
        }
        if ( arg0.getExpiryDate() != null ) {
            arg1.setExpiryDate( arg0.getExpiryDate() );
        }
    }
}
