package com.example.mangobank.service;

import com.example.mangobank.model.dto.LoginDataDto;
import com.example.mangobank.model.dto.UserDtoRequest;
import com.example.mangobank.model.dto.UserDtoResponse;
import com.example.mangobank.model.entity.LoginData;
import com.example.mangobank.model.entity.User;

import java.util.List;

public interface UserService {
    void addUser(UserDtoRequest userDtoRequest);

    void deleteUser(UserDtoRequest userDtoRequest);

    void deleteUserById(Long userId);

    User updateUserInfo(UserDtoRequest userDtoRequest);

    LoginData updateUserLoginData(LoginDataDto loginDataDto);

    List<UserDtoResponse> getAll();

    UserDtoResponse getUserById(Long id);

}
