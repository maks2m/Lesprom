package com.example.lesprom.controller.rest.impl;

import com.example.lesprom.controller.rest.AbstractRestController;
import com.example.lesprom.dto.user.User;
import com.example.lesprom.mapper.UserMapper;
import com.example.lesprom.service.rest.impl.UserRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserRestController extends AbstractRestController<User, UserRestService> {

    protected UserRestController(UserRestService service) {
        super(service);
    }

    @Override
    public Object list(Integer pageNo, Integer pageSize, String sortBy) {
        Page<com.example.lesprom.entity.User> page = service.list(pageNo, pageSize, sortBy);
        List<User> listDto = UserMapper.INSTANCE.mapList(page.getContent());
        return new PageImpl<>(listDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public User getOne(Long id) {
        return UserMapper.INSTANCE.mapSingle(service.getById(id));
    }

    @Override
    public User create(User modelDto) {
        com.example.lesprom.entity.User model = UserMapper.INSTANCE.mapSingle(modelDto);
        return UserMapper.INSTANCE.mapSingle(service.create(model));
    }

    @Override
    public User update(Long id, User modelDto) {
        com.example.lesprom.entity.User model = UserMapper.INSTANCE.mapSingle(modelDto);
        return UserMapper.INSTANCE.mapSingle(service.update(id, model));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

}
