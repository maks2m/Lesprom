package com.example.lesprom.service.rest;

import com.example.lesprom.entity.Baguette;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.BaguetteRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaguetteRestService extends AbstractRestService<Baguette, BaguetteRepo> {

    public BaguetteRestService(BaguetteRepo repository) {
        super(repository);
    }

    @Override
    public List<Baguette> list() {
        return super.repository.findAllByOrderById();
    }

    @Override
    public Baguette getById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Baguette create(Baguette item) {
        item.setId(null);
        return repository.save(item);
    }

    @Override
    public Baguette update(Long id, Baguette item) {
        Baguette itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return repository.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
