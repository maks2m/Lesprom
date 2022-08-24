package com.example.lesprom.service.rest;

import com.example.lesprom.entity.Role;
import com.example.lesprom.entity.User;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.RoleRepo;
import com.example.lesprom.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRestService extends AbstractRestService<User, UserRepo> {

    private final RoleRepo roleRepo;

    public UserRestService(UserRepo repository, RoleRepo roleRepo) {
        super(repository);
        this.roleRepo = roleRepo;
    }

    public User findByUsername(String username) {
        return super.repository.findByUsername(username).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<User> list() {
        return super.repository.findAllByOrderById();
    }

    @Override
    public User getById(Long id) {
        return super.repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public User create(User item) {
        //добавить проверку на наличие такого имени пользователя в БД
        if (repository.findByUsername(item.getUsername()).orElse(null) == null) new Exception();
        item.setId(null);
        setChildren(item);
        return super.repository.save(item);
    }

    @Override
    public User update(Long id, User item) {
        //добавить проверку на наличие такого имени пользователя в БД

        User itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        setChildren(item);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return repository.save(itemFromDB);
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

    private void checkUniqueUsername() {

    }

}
