package com.example.lesprom.service.rest;

import com.example.lesprom.entity.Workplace;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.WorkplaceRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkplaceRestService extends AbstractRestService<Workplace, WorkplaceRepo> {

    public WorkplaceRestService(WorkplaceRepo repository) {
        super(repository);
    }

    @Override
    public List<Workplace> list() {
        return super.repository.findAllByOrderById();
    }

    @Override
    public Workplace getById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Workplace create(Workplace item) {
        item.setId(null);
        return repository.save(item);
    }

    @Override
    public Workplace update(Long id, Workplace item) {
        Workplace itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return repository.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
