package com.example.lesprom.config;

import com.example.lesprom.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return userService::findByUsername;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                    .authorizeRequests().antMatchers("/").permitAll()
                    .anyRequest().authenticated()

                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()

                .and()
                    .logout()
                    .logoutSuccessUrl("/")

                .and()
                    .build();
    }
}
