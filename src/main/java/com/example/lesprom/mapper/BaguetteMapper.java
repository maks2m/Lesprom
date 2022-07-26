package com.example.lesprom.mapper;

import com.example.lesprom.dto.BaguetteDto;
import com.example.lesprom.entity.Baguette;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BaguetteMapper {

    BaguetteMapper INSTANCE = Mappers.getMapper( BaguetteMapper.class );

    BaguetteDto mapSingle(Baguette item);
    @InheritInverseConfiguration
    Baguette mapSingle(BaguetteDto dto);

    List<BaguetteDto> mapList(List<Baguette> itemList);
    @InheritInverseConfiguration
    List<Baguette> mapListInvert(List<BaguetteDto> dtoList);

}
