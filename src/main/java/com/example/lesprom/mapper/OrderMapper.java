package com.example.lesprom.mapper;

import com.example.lesprom.dto.order.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper( OrderMapper.class );

    Order mapSingle(com.example.lesprom.entity.Order item);
    @InheritInverseConfiguration
    com.example.lesprom.entity.Order mapSingle(Order dto);

    List<Order> mapList(List<com.example.lesprom.entity.Order> itemList);
    @InheritInverseConfiguration
    List<com.example.lesprom.entity.Order> mapListInvert(List<Order> dtoList);


}
