package com.example.mangobank.service;

import com.example.mangobank.model.dto.UserDto;
import com.example.mangobank.model.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deleteUser(User user);

    void deleteUserById(Long id);

    boolean findUserByEmail(String email);

    boolean findUserByPhone(String phone);

    User updateUserInfo(User user);

    List<UserDto> getAll();

    Long getIdByEmail(String email);

    Long getIdByPhone(String phone);

}
