package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.EmployeeDto;
import com.example.lesprom.entity.Employee;
import com.example.lesprom.mapper.EmployeeMapper;
import com.example.lesprom.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> list() {
        return EmployeeMapper.INSTANCE.mapList(employeeService.list());
    }

    @GetMapping("{id}")
    public EmployeeDto getOne(@PathVariable Long id) {
        return EmployeeMapper.INSTANCE.mapSingle(employeeService.getById(id));
    }

    @PostMapping
    public EmployeeDto create(@RequestBody EmployeeDto modelDto) {
        Employee model = EmployeeMapper.INSTANCE.mapSingle(modelDto);
        return EmployeeMapper.INSTANCE.mapSingle(employeeService.save(null ,model));
    }

    @PutMapping("{id}")
    public EmployeeDto update(@PathVariable Long id, @RequestBody EmployeeDto modelDto) {
        System.out.println("modelDto " + modelDto.getFullName());
        Employee model = EmployeeMapper.INSTANCE.mapSingle(modelDto);
        System.out.println("model " + model.getFullName());
        return EmployeeMapper.INSTANCE.mapSingle(employeeService.save(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }

}
