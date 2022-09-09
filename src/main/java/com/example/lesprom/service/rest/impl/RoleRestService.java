package com.example.lesprom.service.rest.impl;

import com.example.lesprom.entity.Employee;
import com.example.lesprom.entity.Role;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.RoleRepo;
import com.example.lesprom.service.rest.AbstractRestService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleRestService extends AbstractRestService<Role, RoleRepo> {

    public RoleRestService(RoleRepo repository) {
        super(repository);
    }

    @Override
    public Page<Role> list(Integer pageNo, Integer pageSize, String sortBy) {
        if (pageNo <= -1) {
            Sort sortItem = Sort.by(sortBy);
            return new PageImpl<>(repository.findAll(sortItem));
        } else {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            return repository.findAll(paging);
        }
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
