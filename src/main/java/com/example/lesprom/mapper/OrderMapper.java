package com.example.lesprom.mapper;

import com.example.lesprom.dto.OrderDto;
import com.example.lesprom.entity.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper( OrderMapper.class );

    OrderDto mapSingle(Order item);
    @InheritInverseConfiguration
    Order mapSingle(OrderDto dto);

    List<OrderDto> mapList(List<Order> itemList);
    @InheritInverseConfiguration
    List<Order> mapListInvert(List<OrderDto> dtoList);


}
