package com.example.lesprom.mapper;

import com.example.lesprom.dto.WorkplaceDto;
import com.example.lesprom.entity.Workplace;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkplaceMapper {

    WorkplaceMapper INSTANCE = Mappers.getMapper( WorkplaceMapper.class );

    WorkplaceDto mapSingle(Workplace item);
    @InheritInverseConfiguration
    Workplace mapSingle(WorkplaceDto dto);

    List<WorkplaceDto> mapList(List<Workplace> itemList);
    @InheritInverseConfiguration
    List<Workplace> mapListInvert(List<WorkplaceDto> dtoList);


}
