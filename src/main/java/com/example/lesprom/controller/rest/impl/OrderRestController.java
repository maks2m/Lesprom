package com.example.lesprom.controller.rest.impl;

import com.example.lesprom.controller.rest.AbstractRestController;
import com.example.lesprom.dto.order.Order;
import com.example.lesprom.mapper.OrderMapper;
import com.example.lesprom.service.rest.impl.OrderRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderRestController extends AbstractRestController<Order, OrderRestService> {


    protected OrderRestController(OrderRestService service) {
        super(service);
    }

    @RequestMapping("/sort-on-workplace")
    public Object listOfWorkplace(@RequestParam(name = "idWorkplace") Long idWorkplace) {
        Page<com.example.lesprom.entity.Order> page = service.listOrdersOfWorkplace(idWorkplace);
        List<Order> listDto = OrderMapper.INSTANCE.mapList(page.getContent());
        return new PageImpl<>(listDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public Object list(Integer pageNo, Integer pageSize, String sortBy) {
        Page<com.example.lesprom.entity.Order> page = service.list(pageNo, pageSize, sortBy);
        List<Order> listDto = OrderMapper.INSTANCE.mapList(page.getContent());
        return new PageImpl<>(listDto, page.getPageable(), page.getTotalElements());
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
