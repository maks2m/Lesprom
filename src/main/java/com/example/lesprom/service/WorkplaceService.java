package com.example.lesprom.service;

import com.example.lesprom.entity.Baguette;
import com.example.lesprom.entity.Workplace;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.BaguetteRepo;
import com.example.lesprom.repo.WorkplaceRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkplaceService {

    private final WorkplaceRepo workplaceRepo;

    @Autowired
    public WorkplaceService(WorkplaceRepo workplaceRepo) {
        this.workplaceRepo = workplaceRepo;
    }

    public List<Workplace> list() {
        return workplaceRepo.findAllByOrderById();
    }

    public Workplace create() {
        return new Workplace();
    }

    public Workplace getById(Long id) {
        return workplaceRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    public void save(Long id, Workplace item) {
        Workplace itemFromDB;
        if (id == null) {
            itemFromDB = new Workplace();
        } else {
            itemFromDB = getById(id);
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        workplaceRepo.save(itemFromDB);
    }

    public void delete(Long id) {
        workplaceRepo.deleteById(id);
    }

}
