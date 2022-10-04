package com.example.mangobank.model.dto;

import com.example.mangobank.enumerated.Role;
import com.example.mangobank.model.entity.LoginData;
import com.example.mangobank.model.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto{ //todo create UserRespDto without password and secretWord
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String loginEmail;
    private String password;
    private String secretWord;
    private Date registrationDate;
    private Role role;

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPhone(user.getPhone());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRegistrationDate(user.getRegistrationDate());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public static User to(UserDto userDto){
        User user = new User();
        LoginData loginData = new LoginData();
        user.setLoginData(loginData);
        loginData.setUser(user);
        loginData.setLoginEmail(userDto.getLoginEmail());
        loginData.setPassword(userDto.getPassword());
        loginData.setSecretWord(userDto.getSecretWord());
        user.setFirstName(userDto.getFirstName()); //todo: builder
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setRole(Role.CLIENT);
        return user;
    }

}
