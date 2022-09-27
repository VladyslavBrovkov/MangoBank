package com.example.mangobank.security;

import com.example.mangobank.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/homepage", true)
                .and()
                .logout().and().build();
    }

    @Bean
    protected InMemoryUserDetailsManager configureAuthentication() {
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles(String.valueOf(Role.ADMIN)).build();
        UserDetails client = User.withDefaultPasswordEncoder().username("client").password("client").roles(String.valueOf(Role.CLIENT)).build();
        return new InMemoryUserDetailsManager(List.of(admin, client));
    }

}
