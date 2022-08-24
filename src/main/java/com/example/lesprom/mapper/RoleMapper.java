package com.example.lesprom.mapper;

import com.example.lesprom.dto.role.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper( RoleMapper.class );

    Role mapSingle(com.example.lesprom.entity.Role item);
    @InheritInverseConfiguration
    com.example.lesprom.entity.Role mapSingle(Role dto);

    List<Role> mapList(List<com.example.lesprom.entity.Role> itemList);
    @InheritInverseConfiguration
    List<com.example.lesprom.entity.Role> mapListInvert(List<Role> dtoList);


}
