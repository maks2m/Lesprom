package com.example.lesprom.controller.rest.impl;

import com.example.lesprom.controller.rest.AbstractRestController;
import com.example.lesprom.dto.order.Order;
import com.example.lesprom.mapper.OrderMapper;
import com.example.lesprom.service.rest.impl.OrderRestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderRestController extends AbstractRestController<Order, OrderRestService> {


    protected OrderRestController(OrderRestService service) {
        super(service);
    }

    @Override
    public List<Order> list() {
        return OrderMapper.INSTANCE.mapList(service.list());
    }

    @Override
    public Order getOne(Long id) {
        return OrderMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @Override
    public Order create(Order modelDto) {
        com.example.lesprom.entity.Order model = OrderMapper.INSTANCE.mapSingle(modelDto);
        return OrderMapper.INSTANCE.mapSingle(service.create(model));
    }

    @Override
    public Order update(Long id, Order modelDto) {
        com.example.lesprom.entity.Order model = OrderMapper.INSTANCE.mapSingle(modelDto);
        return OrderMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }


}
