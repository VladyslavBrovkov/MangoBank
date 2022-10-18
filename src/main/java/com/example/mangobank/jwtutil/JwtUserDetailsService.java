package com.example.mangobank.jwtutil;

import com.example.mangobank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired  //todo move binding to the constructor
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginEmail) throws UsernameNotFoundException {
        var customUser = userRepository.findByEmail(loginEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with login: " + loginEmail));
        return new User(customUser.getLoginData().getLoginEmail(),
                customUser.getLoginData().getPassword(),
                new ArrayList<>());
    }
}
