package com.example.lesprom.controller.rest;

import com.example.lesprom.dto.UserDto;
import com.example.lesprom.entity.User;
import com.example.lesprom.mapper.UserMapper;
import com.example.lesprom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> list() {
        return UserMapper.INSTANCE.mapList(userService.list());
    }

    @GetMapping("{id}")
    public UserDto getOne(@PathVariable Long id) {
        return UserMapper.INSTANCE.mapSingle(userService.getById(id));
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto modelDto) {
        User model = UserMapper.INSTANCE.mapSingle(modelDto);
        return UserMapper.INSTANCE.mapSingle(userService.save(null ,model));
    }

    @PutMapping("{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto modelDto) {
        User model = UserMapper.INSTANCE.mapSingle(modelDto);
        return UserMapper.INSTANCE.mapSingle(userService.save(id, model));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
