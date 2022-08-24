package com.example.lesprom.service.rest;

import com.example.lesprom.entity.User;
import com.example.lesprom.entity.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsRestServiceImpl implements UserDetailsService {

    private final UserRestService userRestService;

    @Autowired
    public UserDetailsRestServiceImpl(UserRestService userRestService) {
        this.userRestService = userRestService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRestService.findByUsername(username);
        return new UserDetailsImpl(user);
    }

}
