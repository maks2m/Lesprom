package com.example.lesprom.service;

import com.example.lesprom.entity.Baguette;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.BaguetteRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaguetteService {

    private final BaguetteRepo baguetteRepo;

    @Autowired
    public BaguetteService(BaguetteRepo baguetteRepo) {
        this.baguetteRepo = baguetteRepo;
    }

    public List<Baguette> list() {
        return baguetteRepo.findAllByOrderById();
    }

    public Baguette create() {
        return new Baguette();
    }

    public Baguette getById(Long id) {
        return baguetteRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    public void save(Long id, Baguette item) {
        Baguette itemFromDB;
        if (id == null) {
            itemFromDB = new Baguette();
        } else {
            itemFromDB = getById(id);
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        baguetteRepo.save(itemFromDB);
    }

    public void delete(Long id) {
        baguetteRepo.deleteById(id);
    }

}
