package com.example.lesprom.mapper;

import com.example.lesprom.dto.RoleDto;
import com.example.lesprom.entity.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper( RoleMapper.class );

    RoleDto mapSingle(Role item);
    @InheritInverseConfiguration
    Role mapSingle(RoleDto dto);

    List<RoleDto> mapList(List<Role> itemList);
    @InheritInverseConfiguration
    List<Role> mapListInvert(List<RoleDto> dtoList);


}
