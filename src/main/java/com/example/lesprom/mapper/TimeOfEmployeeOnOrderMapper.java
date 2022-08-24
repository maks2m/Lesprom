package com.example.lesprom.mapper;

import com.example.lesprom.dto.timeofemployeeonorder.TimeOfEmployeeOnOrder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TimeOfEmployeeOnOrderMapper {

    TimeOfEmployeeOnOrderMapper INSTANCE = Mappers.getMapper( TimeOfEmployeeOnOrderMapper.class );

    TimeOfEmployeeOnOrder mapSingle(com.example.lesprom.entity.TimeOfEmployeeOnOrder item);
    @InheritInverseConfiguration
    com.example.lesprom.entity.TimeOfEmployeeOnOrder mapSingle(TimeOfEmployeeOnOrder dto);

    List<TimeOfEmployeeOnOrder> mapList(List<com.example.lesprom.entity.TimeOfEmployeeOnOrder> itemList);
    @InheritInverseConfiguration
    List<com.example.lesprom.entity.TimeOfEmployeeOnOrder> mapListInvert(List<TimeOfEmployeeOnOrder> dtoList);


}
