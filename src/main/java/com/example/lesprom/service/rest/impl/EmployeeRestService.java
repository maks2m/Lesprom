package com.example.lesprom.service.rest.impl;

import com.example.lesprom.entity.Employee;
import com.example.lesprom.entity.Role;
import com.example.lesprom.entity.User;
import com.example.lesprom.entity.Workplace;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.EmployeeRepo;
import com.example.lesprom.repo.WorkplaceRepo;
import com.example.lesprom.service.rest.AbstractRestService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeRestService extends AbstractRestService<Employee, EmployeeRepo> {

    private final WorkplaceRepo workplaceRepo;

    public EmployeeRestService(EmployeeRepo repository, WorkplaceRepo workplaceRepo) {
        super(repository);
        this.workplaceRepo = workplaceRepo;
    }

    @Override
    public List<Employee> list() {
        return super.repository.findAllByOrderById();
    }

    @Override
    public Employee getById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Employee create(Employee item) {
        item.setId(null);
        setChildren(item);
        return repository.save(item);
    }

    @Override
    public Employee update(Long id, Employee item) {
        Employee itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        setChildren(item);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return repository.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * Функция поиска в БД и восстановления дочерних сущностей в родительской по ID
     * @param item сущность, требующая восстановления дочерних сущностей
     */
    private void setChildren(Employee item) {
        item.setWorkplaces(new HashSet<>(workplaceRepo.findAllById(item.getWorkplaces().stream().map(Workplace::getId).collect(Collectors.toSet()))));
    }

}
