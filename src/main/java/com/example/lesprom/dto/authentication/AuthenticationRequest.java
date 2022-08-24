package com.example.lesprom.dto.authentication;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
