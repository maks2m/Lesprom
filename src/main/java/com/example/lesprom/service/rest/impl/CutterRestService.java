package com.example.lesprom.service.rest.impl;

import com.example.lesprom.entity.Cutter;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.CutterRepo;
import com.example.lesprom.service.rest.AbstractRestService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class CutterRestService extends AbstractRestService<Cutter, CutterRepo> {

    public CutterRestService(CutterRepo repository) {
        super(repository);
    }

    @Override
    public Page<Cutter> list(Integer pageNo, Integer pageSize, String sortBy) {
        if (pageNo <= -1) {
            Sort sortItem = Sort.by(sortBy);
            return new PageImpl<>(repository.findAll(sortItem));
        } else {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            return repository.findAll(paging);
        }
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
