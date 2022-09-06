package com.example.lesprom.service.rest.impl;

import com.example.lesprom.entity.Role;
import com.example.lesprom.entity.User;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.exception.NotUniqueUsernameException;
import com.example.lesprom.repo.RoleRepo;
import com.example.lesprom.repo.UserRepo;
import com.example.lesprom.service.rest.AbstractRestService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRestService extends AbstractRestService<User, UserRepo> {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    public UserRestService(UserRepo repository, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    public User findByUsername(String username) {
        return super.repository.findByUsername(username).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<User> list() {
        List<User> users = super.repository.findAllByOrderById();
        users.forEach(u -> u.setPassword(""));
        return users;
    }

    @Override
    public User getById(Long id) {
        User user = super.repository.findById(id).orElseThrow(NotFoundException::new);
        user.setPassword("");
        return user;
    }

    @Override
    public User create(User item) {
        checkUniqueUsername(item);
        item.setId(null);
        setChildren(item);
        item.setPassword(passwordEncoder.encode(item.getPassword()));
        User user = super.repository.save(item);
        user.setPassword("");
        return user;
    }

    @Override
    public User update(Long id, User item) {
        User itemFromDB = super.repository.findById(id).orElseThrow(NotFoundException::new);
        setChildren(item);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        itemFromDB.setPassword(passwordEncoder.encode(itemFromDB.getPassword()));
        User user = super.repository.save(itemFromDB);
        user.setPassword("");
        return user;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * функция поиска в БД и восстановления дочерних сущностей в родительской по ID
     * @param item - сущность, требующая восстановления дочерних сущностей
     */
    private void setChildren(User item) {
        item.setRoles(new HashSet<>(roleRepo.findAllById(item.getRoles().stream().map(Role::getId).collect(Collectors.toSet()))));
    }

    /**
     * функция проверки уникальности username в БД
     * @param item - сущность, требующая проверки уникальности username в БД
     * exception - отправляет статус HttpStatus.CONFLICT
     */
    private void checkUniqueUsername(User item) {
        if (repository.findByUsername(item.getUsername()).orElse(null) != null) throw new NotUniqueUsernameException();
    }

}
