package com.example.lesprom.service.rest.impl;

import com.example.lesprom.entity.Role;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.RoleRepo;
import com.example.lesprom.service.rest.AbstractRestService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleRestService extends AbstractRestService<Role, RoleRepo> {

    public RoleRestService(RoleRepo repository) {
        super(repository);
    }

    @Override
    public List<Role> list() {
        return super.repository.findAllByOrderById();
    }

    @Override
    public Role getById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Role create(Role item) {
        item.setId(null);
        return repository.save(item);
    }

    @Override
    public Role update(Long id, Role item) {
        Role itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return repository.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
