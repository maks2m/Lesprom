package com.example.lesprom.mapper;

import com.example.lesprom.dto.UserDto;
import com.example.lesprom.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDto mapSingle(User item);
    @InheritInverseConfiguration
    User mapSingle(UserDto dto);

    List<UserDto> mapList(List<User> itemList);
    @InheritInverseConfiguration
    List<User> mapListInvert(List<UserDto> dtoList);


}
