package com.example.lesprom.controller.rest.impl;

import com.example.lesprom.controller.rest.AbstractRestController;
import com.example.lesprom.dto.timeofemployeeonorder.TimeOfEmployeeOnOrder;
import com.example.lesprom.mapper.TimeOfEmployeeOnOrderMapper;
import com.example.lesprom.service.rest.impl.TimeOfEmployeeOnOrderRestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/time-of-employee-on-order")
public class TimeOfEmployeeOnOrderRectController extends AbstractRestController<TimeOfEmployeeOnOrder, TimeOfEmployeeOnOrderRestService> {


    protected TimeOfEmployeeOnOrderRectController(TimeOfEmployeeOnOrderRestService service) {
        super(service);
    }

    @Override
    public List<TimeOfEmployeeOnOrder> list() {
        return TimeOfEmployeeOnOrderMapper.INSTANCE.mapList(service.list());
    }

    @Override
    public TimeOfEmployeeOnOrder getOne(Long id) {
        return TimeOfEmployeeOnOrderMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @Override
    public TimeOfEmployeeOnOrder create(TimeOfEmployeeOnOrder modelDto) {
        com.example.lesprom.entity.TimeOfEmployeeOnOrder model = TimeOfEmployeeOnOrderMapper.INSTANCE.mapSingle(modelDto);
        return TimeOfEmployeeOnOrderMapper.INSTANCE.mapSingle(service.create(model));
    }

    @Override
    public TimeOfEmployeeOnOrder update(Long id, TimeOfEmployeeOnOrder modelDto) {
        com.example.lesprom.entity.TimeOfEmployeeOnOrder model = TimeOfEmployeeOnOrderMapper.INSTANCE.mapSingle(modelDto);
        return TimeOfEmployeeOnOrderMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

}
