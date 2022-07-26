package com.example.lesprom.service;

import com.example.lesprom.entity.Employee;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.EmployeeRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> list() {
        return employeeRepo.findAllByOrderById();
    }

    public Employee create() {
        return new Employee();
    }

    public Employee getById(Long id) {
        return employeeRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    public Employee save(Long id, Employee item) {
        Employee itemFromDB;
        if (id == null) {
            itemFromDB = new Employee();
        } else {
            itemFromDB = getById(id);
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return employeeRepo.save(itemFromDB);
    }

    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }

}
