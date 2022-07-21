package com.example.lesprom.service;


import com.example.lesprom.entity.Role;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.RoleRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> list() {
        return roleRepo.findAllByOrderById();
    }

    public Role create() {
        return new Role();
    }

    public Role getById(Long id) {
        return roleRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    public void save(Long id, Role item) {
        Role itemFromDB;
        if (id == null) {
            itemFromDB = new Role();
        } else {
            itemFromDB = getById(id);
        }
        BeanUtils.copyProperties(item, itemFromDB, "id");
        roleRepo.save(itemFromDB);
    }

    public void delete(Long id) {
        roleRepo.deleteById(id);
    }
}
