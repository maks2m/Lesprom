package com.example.lesprom.mapper;

import com.example.lesprom.dto.technologicalprocess.TechnologicalProcess;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TechnologicalProcessMapper {

    TechnologicalProcessMapper INSTANCE = Mappers.getMapper( TechnologicalProcessMapper.class );

    TechnologicalProcess mapSingle(com.example.lesprom.entity.TechnologicalProcess item);
    @InheritInverseConfiguration
    com.example.lesprom.entity.TechnologicalProcess mapSingle(TechnologicalProcess dto);

    List<TechnologicalProcess> mapList(List<com.example.lesprom.entity.TechnologicalProcess> itemList);
    @InheritInverseConfiguration
    List<com.example.lesprom.entity.TechnologicalProcess> mapListInvert(List<TechnologicalProcess> dtoList);

}
