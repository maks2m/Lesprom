package com.example.lesprom.mapper;

import com.example.lesprom.dto.TimeOfEmployeeOnOrderDto;
import com.example.lesprom.entity.TimeOfEmployeeOnOrder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TimeOfEmployeeOnOrderMapper {

    TimeOfEmployeeOnOrderMapper INSTANCE = Mappers.getMapper( TimeOfEmployeeOnOrderMapper.class );

    TimeOfEmployeeOnOrderDto mapSingle(TimeOfEmployeeOnOrder item);
    @InheritInverseConfiguration
    TimeOfEmployeeOnOrder mapSingle(TimeOfEmployeeOnOrderDto dto);

    List<TimeOfEmployeeOnOrderDto> mapList(List<TimeOfEmployeeOnOrder> itemList);
    @InheritInverseConfiguration
    List<TimeOfEmployeeOnOrder> mapListInvert(List<TimeOfEmployeeOnOrderDto> dtoList);


}
