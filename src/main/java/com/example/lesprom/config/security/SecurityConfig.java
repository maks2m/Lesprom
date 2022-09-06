package com.example.lesprom.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // видео по spring security https://www.youtube.com/watch?v=7uxROJ1nduk
    private final JwtConfigurer jwtConfigurer;

    @Autowired
    public SecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .cors()
                .and()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()

                    /*.antMatchers("/api/role").hasAnyRole("ADMIN")
                    .antMatchers("/api/user").hasAnyRole("ADMIN")

                    .antMatchers("/api/baguette").hasAnyRole("MANAGER", "TECHNOLOG", "ADMIN")
                    .antMatchers("/api/cutter").hasAnyRole("MANAGER", "TECHNOLOG", "ADMIN")

                    .antMatchers("/api/employee").hasAnyRole("MANAGER", "TECHNOLOG", "ADMIN", "USER")
                    .antMatchers("/api/order").hasAnyRole("MANAGER", "TECHNOLOG", "ADMIN", "USER")
                    .antMatchers("/api/technological-process").hasAnyRole("MANAGER", "TECHNOLOG", "ADMIN", "USER")
                    .antMatchers("/api/workplace").hasAnyRole("MANAGER", "TECHNOLOG", "ADMIN", "USER")*/

                    .antMatchers("/").permitAll()
                    .antMatchers("/api/auth/login").permitAll()

                    .anyRequest()
                    .authenticated()
                .and()
                    .apply(jwtConfigurer);
    }
}
