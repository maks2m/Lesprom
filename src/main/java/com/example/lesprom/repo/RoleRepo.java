package com.example.lesprom.repo;

import com.example.lesprom.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Long> {
    List<Role> findAllByOrderById();

    Role findByRole(String user);
}
