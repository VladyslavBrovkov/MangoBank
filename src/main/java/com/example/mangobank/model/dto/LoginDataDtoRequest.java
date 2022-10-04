package com.example.mangobank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDataDtoRequest {
    private String loginEmail;
    private String password;
    private String secretWord;
}
