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


    public LoginDataServiceImpl(LoginDataRepository loginDataRepository) {
        this.loginDataRepository = loginDataRepository;
    }

    @Override
    public List<LoginData> getAll() {
        return loginDataRepository.findAll();
    }
}
