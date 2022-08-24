package com.example.lesprom.mapper;

import com.example.lesprom.dto.workplace.Workplace;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkplaceMapper {

    WorkplaceMapper INSTANCE = Mappers.getMapper( WorkplaceMapper.class );

    Workplace mapSingle(com.example.lesprom.entity.Workplace item);
    @InheritInverseConfiguration
    com.example.lesprom.entity.Workplace mapSingle(Workplace dto);

    List<Workplace> mapList(List<com.example.lesprom.entity.Workplace> itemList);
    @InheritInverseConfiguration
    List<com.example.lesprom.entity.Workplace> mapListInvert(List<Workplace> dtoList);


}
