package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.order.Order;
import com.example.lesprom.mapper.OrderMapper;
import com.example.lesprom.service.rest.OrderRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    private final OrderRestService service;

    @Autowired
    public OrderRestController(OrderRestService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> list() {
        return OrderMapper.INSTANCE.mapList(service.list());
    }

    @GetMapping("{id}")
    public Order getOne(@PathVariable Long id) {
        return OrderMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @PostMapping
    public Order create(@RequestBody Order modelDto) {
        com.example.lesprom.entity.Order model = OrderMapper.INSTANCE.mapSingle(modelDto);
        return OrderMapper.INSTANCE.mapSingle(service.create(model));
    }

    @PutMapping("{id}")
    public Order update(@PathVariable Long id, @RequestBody Order modelDto) {
        com.example.lesprom.entity.Order model = OrderMapper.INSTANCE.mapSingle(modelDto);
        return OrderMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


}
