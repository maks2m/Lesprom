package com.example.lesprom.mapper;

import com.example.lesprom.dto.cutter.Cutter;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CutterMapper {

    CutterMapper INSTANCE = Mappers.getMapper( CutterMapper.class );

    Cutter mapSingle(com.example.lesprom.entity.Cutter item);
    @InheritInverseConfiguration
    com.example.lesprom.entity.Cutter mapSingle(Cutter dto);

    List<Cutter> mapList(List<com.example.lesprom.entity.Cutter> itemList);
    @InheritInverseConfiguration
    List<com.example.lesprom.entity.Cutter> mapListInvert(List<Cutter> dtoList);


}
