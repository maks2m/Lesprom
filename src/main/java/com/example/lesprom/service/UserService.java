package com.example.lesprom.service;

import com.example.lesprom.entity.Role;
import com.example.lesprom.entity.User;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.RoleRepo;
import com.example.lesprom.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    public List<User> list() {
        List<User> allByOrderById = userRepo.findAllByOrderById();
        return allByOrderById;
    }

    public User create() {
        return new User();
    }

    public User getById(Long id) {
        return userRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(NotFoundException::new);
    }

    public boolean save(Long id, Map<String, String> mapModel, Model model) {
        User itemFromDB;
        if (id == null) {
            itemFromDB = new User();
            itemFromDB.getRoles().add(roleRepo.findByRole("USER"));
        } else {
            itemFromDB = getById(id);
        }
        itemFromDB.getRoles().clear();
        Map<String, Role> roles = roleRepo.findAllByOrderById().stream().collect(Collectors.toMap(Role::getRole, r -> r));
        System.out.println();
        for (String key : mapModel.keySet()) {
            if (roles.containsKey(key)) {
                itemFromDB.getRoles().add(roles.get(key));
            }
            switch (key) {
                case "username":
                    itemFromDB.setUsername(mapModel.get(key));
                    break;
                case "password":
                    if (!mapModel.get(key).equals(""))
                        itemFromDB.setPassword(passwordEncoder.encode(mapModel.get(key)));
                    break;
            }
        }

        if (userRepo.findByUsername(mapModel.get("username")) != null) {
            model.addAttribute("errorAddUser", "User exists!");
            model.addAttribute("user", itemFromDB);
            model.addAttribute("roleList", roleRepo.findAllByOrderById());
            return false;
        } else {
            userRepo.save(itemFromDB);
            return true;
        }
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

}
