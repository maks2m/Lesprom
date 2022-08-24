package com.example.lesprom.service.rest;

import com.example.lesprom.entity.Cutter;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.CutterRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CutterRestService extends AbstractRestService<Cutter, CutterRepo>{

    public CutterRestService(CutterRepo repository) {
        super(repository);
    }

    @Override
    public List<Cutter> list() {
        return super.repository.findAllByOrderById();
    }

    @Override
    public Cutter getById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Cutter create(Cutter item) {
        item.setId(null);
        return repository.save(item);
    }

    @Override
    public Cutter update(Long id, Cutter item) {
        Cutter itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return repository.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
