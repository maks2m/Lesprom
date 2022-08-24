package com.example.lesprom.mapper;

import com.example.lesprom.dto.user.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    User mapSingle(com.example.lesprom.entity.User item);
    @InheritInverseConfiguration
    com.example.lesprom.entity.User mapSingle(User dto);

    List<User> mapList(List<com.example.lesprom.entity.User> itemList);
    @InheritInverseConfiguration
    List<com.example.lesprom.entity.User> mapListInvert(List<User> dtoList);


}
