package com.example.mangobank.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class LoginData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String loginEmail;
    private String password;
    private String secretWord;
    @OneToOne(mappedBy = "loginData")
    private User user;
}
