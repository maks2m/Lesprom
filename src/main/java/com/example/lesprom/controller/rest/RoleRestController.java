package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.role.Role;
import com.example.lesprom.mapper.RoleMapper;
import com.example.lesprom.service.rest.RoleRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleRestController {

    private final RoleRestService service;

    @Autowired
    public RoleRestController(RoleRestService service) {
        this.service = service;
    }

    @GetMapping
    public List<Role> list() {
        return RoleMapper.INSTANCE.mapList(service.list());
    }

    @GetMapping("{id}")
    public Role getOne(@PathVariable Long id) {
        return RoleMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @PostMapping
    public Role create(@RequestBody Role modelDto) {
        com.example.lesprom.entity.Role model = RoleMapper.INSTANCE.mapSingle(modelDto);
        return RoleMapper.INSTANCE.mapSingle(service.create(model));
    }

    @PutMapping("{id}")
    public Role update(@PathVariable Long id, @RequestBody Role modelDto) {
        com.example.lesprom.entity.Role model = RoleMapper.INSTANCE.mapSingle(modelDto);
        return RoleMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
