package com.example.mangobank.service;

import com.example.mangobank.model.entity.LoginData;
import com.example.mangobank.model.entity.User;

import java.util.List;

public interface LoginDataService {

    void updateLoginData(User user);

    User getUserByEmail(String email);

    List<LoginData> getAll();
}
