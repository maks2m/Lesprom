package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.user.User;
import com.example.lesprom.mapper.UserMapper;
import com.example.lesprom.service.rest.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserRestService service;

    @Autowired
    public UserRestController(UserRestService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> list() {
        return UserMapper.INSTANCE.mapList(service.list());
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable Long id) {
        return UserMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @PostMapping
    public User create(@RequestBody User modelDto) {
        com.example.lesprom.entity.User model = UserMapper.INSTANCE.mapSingle(modelDto);
        return UserMapper.INSTANCE.mapSingle(service.create(model));
    }

    @PutMapping("{id}")
    public User update(@PathVariable Long id, @RequestBody User modelDto) {
        com.example.lesprom.entity.User model = UserMapper.INSTANCE.mapSingle(modelDto);
        return UserMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
