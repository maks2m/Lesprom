package com.example.lesprom.service.rest.impl;

import com.example.lesprom.entity.Employee;
import com.example.lesprom.entity.TechnologicalProcess;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.EmployeeRepo;
import com.example.lesprom.repo.OrderRepo;
import com.example.lesprom.repo.TechnologicalProcessRepo;
import com.example.lesprom.repo.WorkplaceRepo;
import com.example.lesprom.service.rest.AbstractRestService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TechnologicalProcessService extends AbstractRestService<TechnologicalProcess, TechnologicalProcessRepo> {

    private final EmployeeRepo employeeRepo;
    private final OrderRepo orderRepo;
    private final WorkplaceRepo workplaceRepo;

    public TechnologicalProcessService(TechnologicalProcessRepo repository, EmployeeRepo employeeRepo, OrderRepo orderRepo, WorkplaceRepo workplaceRepo) {
        super(repository);
        this.employeeRepo = employeeRepo;
        this.orderRepo = orderRepo;
        this.workplaceRepo = workplaceRepo;
    }

    @Override
    public Page<TechnologicalProcess> list(Integer pageNo, Integer pageSize, String sortBy) {
        if (pageNo <= -1) {
            Sort sortItem = Sort.by(sortBy);
            return new PageImpl<>(repository.findAll(sortItem));
        } else {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            return repository.findAll(paging);
        }
    }

    @Override
    public TechnologicalProcess getById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public TechnologicalProcess create(TechnologicalProcess item) {
        item.setId(null);
        setChildren(item);
        return repository.save(item);
    }

    @Override
    public TechnologicalProcess update(Long id, TechnologicalProcess item) {
        TechnologicalProcess itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
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
    private void setChildren(TechnologicalProcess item) {
        item.setEmployee(employeeRepo.findById(item.getEmployee().getId()).orElseThrow(NotFoundException::new));
        item.setOrder(orderRepo.findById(item.getOrder().getId()).orElseThrow(NotFoundException::new));
        item.setWorkplace(workplaceRepo.findById(item.getWorkplace().getId()).orElseThrow(NotFoundException::new));
    }


}
