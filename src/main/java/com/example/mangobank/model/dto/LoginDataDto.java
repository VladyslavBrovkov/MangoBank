package com.example.mangobank.model.dto;

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
}