package com.example.mangobank.service;

import com.example.mangobank.model.dto.LoginDataDto;
import com.example.mangobank.model.entity.LoginData;
import com.example.mangobank.model.entity.User;

import java.util.List;

public interface LoginDataService {
    List<LoginData> getAll();
}
