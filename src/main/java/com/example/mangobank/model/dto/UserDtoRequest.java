package com.example.mangobank.model.dto;

import com.example.mangobank.enumerated.Role;
import com.example.mangobank.model.entity.LoginData;
import com.example.mangobank.model.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDtoRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String loginEmail;
    private String password;
    private String secretWord;
    private Date registrationDate;
    private Role role;


    public static User to(UserDtoRequest userDtoRequest) {
        User user = new User();
        LoginData loginData = new LoginData();
        user.setLoginData(loginData);
        loginData.setUser(user);
        loginData.setLoginEmail(userDtoRequest.getLoginEmail());
        loginData.setPassword(userDtoRequest.getPassword());
        loginData.setSecretWord(userDtoRequest.getSecretWord());
        user.setFirstName(userDtoRequest.getFirstName()); //todo: builder
        user.setLastName(userDtoRequest.getLastName());
        user.setPhone(userDtoRequest.getPhone());
        if (userDtoRequest.getRole() == null) {
            user.setRole(Role.CLIENT);
        } else {
            user.setRole(userDtoRequest.getRole());
        }
        return user;
    }

    public User updateUserInfo(User user) {
        user.setPhone(phone);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

}
