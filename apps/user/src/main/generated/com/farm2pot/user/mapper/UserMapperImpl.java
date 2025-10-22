package com.farm2pot.user.mapper;

import com.farm2pot.user.dto.UserDto;
import com.farm2pot.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-22T11:00:16+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        if ( dto.getLoginId() != null ) {
            user.loginId( dto.getLoginId() );
        }
        if ( dto.getEmail() != null ) {
            user.email( dto.getEmail() );
        }
        if ( dto.getPassword() != null ) {
            user.password( dto.getPassword() );
        }
        if ( dto.getName() != null ) {
            user.name( dto.getName() );
        }
        if ( dto.getLoginType() != null ) {
            user.loginType( dto.getLoginType() );
        }
        if ( dto.getPhoneNo() != null ) {
            user.phoneNo( dto.getPhoneNo() );
        }
        if ( dto.getBirthDay() != null ) {
            user.birthDay( dto.getBirthDay() );
        }
        user.status( dto.getStatus() );
        if ( dto.getGender() != null ) {
            user.gender( dto.getGender() );
        }
        if ( dto.getNickName() != null ) {
            user.nickName( dto.getNickName() );
        }
        List<String> list = dto.getRoles();
        if ( list != null ) {
            user.roles( new ArrayList<String>( list ) );
        }

        User userResult = user.build();

        afterUpdate( dto, userResult );

        return userResult;
    }

    @Override
    public UserDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        if ( entity.getId() != null ) {
            userDto.id( entity.getId() );
        }
        if ( entity.getLoginId() != null ) {
            userDto.loginId( entity.getLoginId() );
        }
        if ( entity.getEmail() != null ) {
            userDto.email( entity.getEmail() );
        }
        if ( entity.getPassword() != null ) {
            userDto.password( entity.getPassword() );
        }
        if ( entity.getName() != null ) {
            userDto.name( entity.getName() );
        }
        if ( entity.getLoginType() != null ) {
            userDto.loginType( entity.getLoginType() );
        }
        if ( entity.getPhoneNo() != null ) {
            userDto.phoneNo( entity.getPhoneNo() );
        }
        if ( entity.getBirthDay() != null ) {
            userDto.birthDay( entity.getBirthDay() );
        }
        userDto.status( entity.getStatus() );
        if ( entity.getGender() != null ) {
            userDto.gender( entity.getGender() );
        }
        if ( entity.getNickName() != null ) {
            userDto.nickName( entity.getNickName() );
        }
        List<String> list = entity.getRoles();
        if ( list != null ) {
            userDto.roles( new ArrayList<String>( list ) );
        }

        return userDto.build();
    }

    @Override
    public void updateEntityFromDto(UserDto dto, User entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getLoginId() != null ) {
            entity.setLoginId( dto.getLoginId() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getLoginType() != null ) {
            entity.setLoginType( dto.getLoginType() );
        }
        if ( dto.getPhoneNo() != null ) {
            entity.setPhoneNo( dto.getPhoneNo() );
        }
        if ( dto.getBirthDay() != null ) {
            entity.setBirthDay( dto.getBirthDay() );
        }
        entity.setStatus( dto.getStatus() );
        if ( dto.getGender() != null ) {
            entity.setGender( dto.getGender() );
        }
        if ( dto.getNickName() != null ) {
            entity.setNickName( dto.getNickName() );
        }

        afterUpdate( dto, entity );
    }
}
