package com.farm2pot.user.mapper;

import com.farm2pot.user.dto.UserAddressDto;
import com.farm2pot.user.entity.UserAddress;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-31T00:00:38+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class UserAddressMapperImpl implements UserAddressMapper {

    @Override
    public UserAddress toEntity(UserAddressDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserAddress.UserAddressBuilder userAddress = UserAddress.builder();

        if ( arg0.getRecipientName() != null ) {
            userAddress.recipientName( arg0.getRecipientName() );
        }
        if ( arg0.getPhoneNumber() != null ) {
            userAddress.phoneNumber( arg0.getPhoneNumber() );
        }
        if ( arg0.getPostalCode() != null ) {
            userAddress.postalCode( arg0.getPostalCode() );
        }
        if ( arg0.getAddressLine1() != null ) {
            userAddress.addressLine1( arg0.getAddressLine1() );
        }
        if ( arg0.getAddressLine2() != null ) {
            userAddress.addressLine2( arg0.getAddressLine2() );
        }
        if ( arg0.getCreatedAt() != null ) {
            userAddress.createdAt( arg0.getCreatedAt() );
        }
        if ( arg0.getUpdatedAt() != null ) {
            userAddress.updatedAt( arg0.getUpdatedAt() );
        }

        return userAddress.build();
    }

    @Override
    public UserAddressDto toDto(UserAddress arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserAddressDto.UserAddressDtoBuilder userAddressDto = UserAddressDto.builder();

        if ( arg0.getId() != null ) {
            userAddressDto.id( arg0.getId() );
        }
        if ( arg0.getRecipientName() != null ) {
            userAddressDto.recipientName( arg0.getRecipientName() );
        }
        if ( arg0.getPhoneNumber() != null ) {
            userAddressDto.phoneNumber( arg0.getPhoneNumber() );
        }
        if ( arg0.getPostalCode() != null ) {
            userAddressDto.postalCode( arg0.getPostalCode() );
        }
        if ( arg0.getAddressLine1() != null ) {
            userAddressDto.addressLine1( arg0.getAddressLine1() );
        }
        if ( arg0.getAddressLine2() != null ) {
            userAddressDto.addressLine2( arg0.getAddressLine2() );
        }
        if ( arg0.getCreatedAt() != null ) {
            userAddressDto.createdAt( arg0.getCreatedAt() );
        }
        if ( arg0.getUpdatedAt() != null ) {
            userAddressDto.updatedAt( arg0.getUpdatedAt() );
        }

        return userAddressDto.build();
    }

    @Override
    public void updateEntityFromDto(UserAddressDto dto, UserAddress entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getRecipientName() != null ) {
            entity.setRecipientName( dto.getRecipientName() );
        }
        if ( dto.getPhoneNumber() != null ) {
            entity.setPhoneNumber( dto.getPhoneNumber() );
        }
        if ( dto.getPostalCode() != null ) {
            entity.setPostalCode( dto.getPostalCode() );
        }
        if ( dto.getAddressLine1() != null ) {
            entity.setAddressLine1( dto.getAddressLine1() );
        }
        if ( dto.getAddressLine2() != null ) {
            entity.setAddressLine2( dto.getAddressLine2() );
        }
        entity.setDefault( dto.isDefault() );
        if ( dto.getCreatedAt() != null ) {
            entity.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            entity.setUpdatedAt( dto.getUpdatedAt() );
        }
    }
}
