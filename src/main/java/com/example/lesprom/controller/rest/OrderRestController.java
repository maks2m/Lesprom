package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.OrderDto;
import com.example.lesprom.entity.Order;
import com.example.lesprom.mapper.OrderMapper;
import com.example.lesprom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> list() {
        return OrderMapper.INSTANCE.mapList(orderService.list());
    }

    @GetMapping("{id}")
    public OrderDto getOne(@PathVariable Long id) {
        return OrderMapper.INSTANCE.mapSingle(orderService.getById(id));
    }

    @PostMapping
    public OrderDto create(@RequestBody OrderDto modelDto) {
        Order model = OrderMapper.INSTANCE.mapSingle(modelDto);
        return OrderMapper.INSTANCE.mapSingle(orderService.save(null ,model));
    }

    @PutMapping("{id}")
    public OrderDto update(@PathVariable Long id, @RequestBody OrderDto modelDto) {
        Order model = OrderMapper.INSTANCE.mapSingle(modelDto);
        return OrderMapper.INSTANCE.mapSingle(orderService.save(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }


}
