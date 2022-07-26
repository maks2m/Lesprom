package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.RoleDto;
import com.example.lesprom.entity.Role;
import com.example.lesprom.mapper.RoleMapper;
import com.example.lesprom.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleRestController {

    private final RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDto> list() {
        return RoleMapper.INSTANCE.mapList(roleService.list());
    }

    @GetMapping("{id}")
    public RoleDto getOne(@PathVariable Long id) {
        return RoleMapper.INSTANCE.mapSingle(roleService.getById(id));
    }

    @PostMapping
    public RoleDto create(@RequestBody RoleDto modelDto) {
        Role model = RoleMapper.INSTANCE.mapSingle(modelDto);
        return RoleMapper.INSTANCE.mapSingle(roleService.save(null ,model));
    }

    @PutMapping("{id}")
    public RoleDto update(@PathVariable Long id, @RequestBody RoleDto modelDto) {
        Role model = RoleMapper.INSTANCE.mapSingle(modelDto);
        return RoleMapper.INSTANCE.mapSingle(roleService.save(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }

}
