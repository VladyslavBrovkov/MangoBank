package com.example.mangobank.service.impl;

import com.example.mangobank.model.entity.LoginData;
import com.example.mangobank.model.entity.User;
import com.example.mangobank.repository.LoginDataRepository;
import com.example.mangobank.repository.UserRepository;
import com.example.mangobank.service.LoginDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginDataServiceImpl implements LoginDataService {

    private final LoginDataRepository loginDataRepository;

    private final UserRepository userRepository;

    public LoginDataServiceImpl(LoginDataRepository loginDataRepository, UserRepository userRepository) {
        this.loginDataRepository = loginDataRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void updateLoginData(User user) {

    }
    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<LoginData> getAll() {
        return null;
    }
}
