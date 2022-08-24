package com.example.lesprom.service;

import com.example.lesprom.entity.TimeOfEmployeeOnOrder;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.TimeOfEmployeeOnOrderRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeOfEmployeeOnOrderService {

    private final TimeOfEmployeeOnOrderRepo timeOfEmployeeOnOrderRepo;

    @Autowired
    public TimeOfEmployeeOnOrderService(TimeOfEmployeeOnOrderRepo timeOfEmployeeOnOrderRepo) {
        this.timeOfEmployeeOnOrderRepo = timeOfEmployeeOnOrderRepo;
    }

    public List<TimeOfEmployeeOnOrder> list() {
        return timeOfEmployeeOnOrderRepo.findAllByOrderById();
    }

    public TimeOfEmployeeOnOrder create() {
        return new TimeOfEmployeeOnOrder();
    }

    public TimeOfEmployeeOnOrder getById(Long id) {
        return timeOfEmployeeOnOrderRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    public TimeOfEmployeeOnOrder save(Long id, TimeOfEmployeeOnOrder item) {
        TimeOfEmployeeOnOrder itemFromDB;
        if (id == null) {
            itemFromDB = new TimeOfEmployeeOnOrder();
        } else {
            itemFromDB = getById(id);
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return timeOfEmployeeOnOrderRepo.save(itemFromDB);
    }

    public void delete(Long id) {
        timeOfEmployeeOnOrderRepo.deleteById(id);
    }

}
