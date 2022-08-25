package com.example.lesprom.service.rest.impl;

import com.example.lesprom.entity.TimeOfEmployeeOnOrder;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.EmployeeRepo;
import com.example.lesprom.repo.OrderRepo;
import com.example.lesprom.repo.TimeOfEmployeeOnOrderRepo;
import com.example.lesprom.service.rest.AbstractRestService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeOfEmployeeOnOrderRestService extends AbstractRestService<TimeOfEmployeeOnOrder, TimeOfEmployeeOnOrderRepo> {

    private final EmployeeRepo employeeRepo;
    private final OrderRepo orderRepo;

    public TimeOfEmployeeOnOrderRestService(TimeOfEmployeeOnOrderRepo repository, EmployeeRepo employeeRepo, OrderRepo orderRepo) {
        super(repository);
        this.employeeRepo = employeeRepo;
        this.orderRepo = orderRepo;
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
        setChildren(item);
        return repository.save(item);
    }

    @Override
    public TimeOfEmployeeOnOrder update(Long id, TimeOfEmployeeOnOrder item) {
        TimeOfEmployeeOnOrder itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        setChildren(item);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return repository.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * функция поиска в БД и восстановления дочерних сущностей в родительской по ID
     * @param item - сущность, требующая восстановления дочерних сущностей
     */
    private void setChildren(TimeOfEmployeeOnOrder item) {
        item.setEmployee(employeeRepo.findById(item.getEmployee().getId()).orElseThrow(NotFoundException::new));
        item.setOrder(orderRepo.findById(item.getOrder().getId()).orElseThrow(NotFoundException::new));
    }

}
