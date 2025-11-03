package com.farm2pot.address.mapper;

import com.farm2pot.address.controller.dto.AddressDto;
import com.farm2pot.address.entity.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-03T19:12:27+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toEntity(AddressDto dto) {
        if ( dto == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        if ( dto.getUser() != null ) {
            address.user( dto.getUser() );
        }
        if ( dto.getRecipientName() != null ) {
            address.recipientName( dto.getRecipientName() );
        }
        if ( dto.getPhoneNumber() != null ) {
            address.phoneNumber( dto.getPhoneNumber() );
        }
        if ( dto.getPostalCode() != null ) {
            address.postalCode( dto.getPostalCode() );
        }
        if ( dto.getAddressLine1() != null ) {
            address.addressLine1( dto.getAddressLine1() );
        }
        if ( dto.getAddressLine2() != null ) {
            address.addressLine2( dto.getAddressLine2() );
        }
        if ( dto.getCreatedAt() != null ) {
            address.createdAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            address.updatedAt( dto.getUpdatedAt() );
        }

        return address.build();
    }

    @Override
    public AddressDto toDto(Address entity) {
        if ( entity == null ) {
            return null;
        }

        AddressDto.AddressDtoBuilder addressDto = AddressDto.builder();

        if ( entity.getId() != null ) {
            addressDto.id( entity.getId() );
        }
        if ( entity.getUser() != null ) {
            addressDto.user( entity.getUser() );
        }
        if ( entity.getRecipientName() != null ) {
            addressDto.recipientName( entity.getRecipientName() );
        }
        if ( entity.getPhoneNumber() != null ) {
            addressDto.phoneNumber( entity.getPhoneNumber() );
        }
        if ( entity.getPostalCode() != null ) {
            addressDto.postalCode( entity.getPostalCode() );
        }
        if ( entity.getAddressLine1() != null ) {
            addressDto.addressLine1( entity.getAddressLine1() );
        }
        if ( entity.getAddressLine2() != null ) {
            addressDto.addressLine2( entity.getAddressLine2() );
        }
        if ( entity.getCreatedAt() != null ) {
            addressDto.createdAt( entity.getCreatedAt() );
        }
        if ( entity.getUpdatedAt() != null ) {
            addressDto.updatedAt( entity.getUpdatedAt() );
        }

        return addressDto.build();
    }

    @Override
    public void updateEntityFromDto(AddressDto dto, Address entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getUser() != null ) {
            entity.setUser( dto.getUser() );
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
