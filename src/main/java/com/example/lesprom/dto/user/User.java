package com.example.lesprom.dto.user;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
