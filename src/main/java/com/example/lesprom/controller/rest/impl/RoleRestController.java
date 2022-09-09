package com.example.lesprom.controller.rest.impl;

import com.example.lesprom.controller.rest.AbstractRestController;
import com.example.lesprom.dto.role.Role;
import com.example.lesprom.mapper.RoleMapper;
import com.example.lesprom.service.rest.impl.RoleRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleRestController extends AbstractRestController<Role, RoleRestService> {


    protected RoleRestController(RoleRestService service) {
        super(service);
    }

    @Override
    public Object list(Integer pageNo, Integer pageSize, String sortBy) {
        Page<com.example.lesprom.entity.Role> page = service.list(pageNo, pageSize, sortBy);
        List<Role> listDto = RoleMapper.INSTANCE.mapList(page.getContent());
        return new PageImpl<>(listDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public Role getOne(Long id) {
        return RoleMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @Override
    public Role create(Role modelDto) {
        com.example.lesprom.entity.Role model = RoleMapper.INSTANCE.mapSingle(modelDto);
        return RoleMapper.INSTANCE.mapSingle(service.create(model));
    }

    @Override
    public Role update(Long id, Role modelDto) {
        com.example.lesprom.entity.Role model = RoleMapper.INSTANCE.mapSingle(modelDto);
        return RoleMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

}
