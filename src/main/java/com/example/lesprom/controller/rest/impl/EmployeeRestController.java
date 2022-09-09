package com.example.lesprom.controller.rest.impl;

import com.example.lesprom.controller.rest.AbstractRestController;
import com.example.lesprom.dto.employee.Employee;
import com.example.lesprom.mapper.EmployeeMapper;
import com.example.lesprom.service.rest.impl.EmployeeRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController extends AbstractRestController<Employee, EmployeeRestService> {


    protected EmployeeRestController(EmployeeRestService service) {
        super(service);
    }

    @Override
    public Object list(Integer pageNo, Integer pageSize, String sortBy) {
        Page<com.example.lesprom.entity.Employee> page = service.list(pageNo, pageSize, sortBy);
        List<Employee> listDto = EmployeeMapper.INSTANCE.mapList(page.getContent());
        return new PageImpl<>(listDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public Employee getOne(Long id) {
        return EmployeeMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @Override
    public Employee create(Employee modelDto) {
        com.example.lesprom.entity.Employee model = EmployeeMapper.INSTANCE.mapSingle(modelDto);
        return EmployeeMapper.INSTANCE.mapSingle(service.create(model));
    }

    @Override
    public Employee update(Long id, Employee modelDto) {
        com.example.lesprom.entity.Employee model = EmployeeMapper.INSTANCE.mapSingle(modelDto);
        return EmployeeMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

}
