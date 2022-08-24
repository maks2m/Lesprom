package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.employee.Employee;
import com.example.lesprom.mapper.EmployeeMapper;
import com.example.lesprom.service.rest.EmployeeRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    private final EmployeeRestService service;

    @Autowired
    public EmployeeRestController(EmployeeRestService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> list() {
        return EmployeeMapper.INSTANCE.mapList(service.list());
    }

    @GetMapping("{id}")
    public Employee getOne(@PathVariable Long id) {
        return EmployeeMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @PostMapping
    public Employee create(@RequestBody Employee modelDto) {
        com.example.lesprom.entity.Employee model = EmployeeMapper.INSTANCE.mapSingle(modelDto);
        return EmployeeMapper.INSTANCE.mapSingle(service.create(model));
    }

    @PutMapping("{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee modelDto) {
        com.example.lesprom.entity.Employee model = EmployeeMapper.INSTANCE.mapSingle(modelDto);
        return EmployeeMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
