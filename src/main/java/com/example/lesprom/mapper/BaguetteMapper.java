package com.example.lesprom.mapper;

import com.example.lesprom.dto.baguette.Baguette;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BaguetteMapper {

    BaguetteMapper INSTANCE = Mappers.getMapper( BaguetteMapper.class );

    Baguette mapSingle(com.example.lesprom.entity.Baguette item);
    @InheritInverseConfiguration
    com.example.lesprom.entity.Baguette mapSingle(Baguette dto);

    List<Baguette> mapList(List<com.example.lesprom.entity.Baguette> itemList);
    @InheritInverseConfiguration
    List<com.example.lesprom.entity.Baguette> mapListInvert(List<Baguette> dtoList);

}
