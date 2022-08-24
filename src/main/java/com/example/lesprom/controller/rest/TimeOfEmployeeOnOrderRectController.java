package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.timeofemployeeonorder.TimeOfEmployeeOnOrder;
import com.example.lesprom.mapper.TimeOfEmployeeOnOrderMapper;
import com.example.lesprom.service.rest.TimeOfEmployeeOnOrderRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-of-employee-on-order")
public class TimeOfEmployeeOnOrderRectController {

    private final TimeOfEmployeeOnOrderRestService service;

    @Autowired
    public TimeOfEmployeeOnOrderRectController(TimeOfEmployeeOnOrderRestService service) {
        this.service = service;
    }

    @GetMapping
    public List<TimeOfEmployeeOnOrder> list() {
        return TimeOfEmployeeOnOrderMapper.INSTANCE.mapList(service.list());
    }

    @GetMapping("{id}")
    public TimeOfEmployeeOnOrder getOne(@PathVariable Long id) {
        return TimeOfEmployeeOnOrderMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @PostMapping
    public TimeOfEmployeeOnOrder create(@RequestBody TimeOfEmployeeOnOrder modelDto) {
        com.example.lesprom.entity.TimeOfEmployeeOnOrder model = TimeOfEmployeeOnOrderMapper.INSTANCE.mapSingle(modelDto);
        return TimeOfEmployeeOnOrderMapper.INSTANCE.mapSingle(service.create(model));
    }

    @PutMapping("{id}")
    public TimeOfEmployeeOnOrder update(@PathVariable Long id, @RequestBody TimeOfEmployeeOnOrder modelDto) {
        com.example.lesprom.entity.TimeOfEmployeeOnOrder model = TimeOfEmployeeOnOrderMapper.INSTANCE.mapSingle(modelDto);
        return TimeOfEmployeeOnOrderMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
