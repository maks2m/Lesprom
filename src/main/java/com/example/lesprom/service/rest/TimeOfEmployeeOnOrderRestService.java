package com.example.lesprom.service.rest;

import com.example.lesprom.entity.TimeOfEmployeeOnOrder;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.TimeOfEmployeeOnOrderRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeOfEmployeeOnOrderRestService extends AbstractRestService<TimeOfEmployeeOnOrder, TimeOfEmployeeOnOrderRepo> {

    public TimeOfEmployeeOnOrderRestService(TimeOfEmployeeOnOrderRepo repository) {
        super(repository);
    }

    @Override
    public List<TimeOfEmployeeOnOrder> list() {
        return super.repository.findAllByOrderById();
    }

    @Override
    public TimeOfEmployeeOnOrder getById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public TimeOfEmployeeOnOrder create(TimeOfEmployeeOnOrder item) {
        item.setId(null);
        return repository.save(item);
    }

    @Override
    public TimeOfEmployeeOnOrder update(Long id, TimeOfEmployeeOnOrder item) {
        TimeOfEmployeeOnOrder itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return repository.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
