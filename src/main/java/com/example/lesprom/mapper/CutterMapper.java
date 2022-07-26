package com.example.lesprom.mapper;

import com.example.lesprom.dto.CutterDto;
import com.example.lesprom.entity.Cutter;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CutterMapper {

    CutterMapper INSTANCE = Mappers.getMapper( CutterMapper.class );

    CutterDto mapSingle(Cutter item);
    @InheritInverseConfiguration
    Cutter mapSingle(CutterDto dto);

    List<CutterDto> mapList(List<Cutter> itemList);
    @InheritInverseConfiguration
    List<Cutter> mapListInvert(List<CutterDto> dtoList);


}
