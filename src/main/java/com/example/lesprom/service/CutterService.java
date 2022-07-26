package com.example.lesprom.service;

import com.example.lesprom.entity.Cutter;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.CutterRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CutterService {

    private final CutterRepo cutterRepo;

    @Autowired
    public CutterService(CutterRepo cutterRepo) {
        this.cutterRepo = cutterRepo;
    }

    public List<Cutter> list() {
        return cutterRepo.findAllByOrderById();
    }

    public Cutter create() {
        return new Cutter();
    }

    public Cutter getById(Long id) {
        return cutterRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    public Cutter save(Long id, Cutter item) {
        Cutter itemFromDB;
        if (id == null) {
            itemFromDB = new Cutter();
        } else {
            itemFromDB = getById(id);
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return cutterRepo.save(itemFromDB);
    }

    public void delete(Long id) {
        cutterRepo.deleteById(id);
    }

}
