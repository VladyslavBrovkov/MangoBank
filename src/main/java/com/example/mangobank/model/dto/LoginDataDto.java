package com.example.mangobank.model.dto;

import com.example.mangobank.model.entity.LoginData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDataDto {
    private String newLoginEmail;
    private String newPassword;
    private String oldSecretWord;
    private String newSecretWord;

    public LoginData updateLoginData(LoginData loginData) {
        loginData.setLoginEmail(newLoginEmail);
        loginData.setSecretWord(newSecretWord);
        loginData.setPassword(newPassword);
        return loginData;
    }
}
