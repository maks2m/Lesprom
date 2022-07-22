package com.example.lesprom.repo;

import com.example.lesprom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.roles where u.username=:username")
    Optional<User> findByUsername(String username);

    @Query("select distinct u from User u join fetch u.roles order by u.id")
    List<User> findAllByOrderById();
}
