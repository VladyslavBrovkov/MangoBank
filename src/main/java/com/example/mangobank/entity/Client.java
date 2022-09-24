package com.example.mangobank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Account account;

    private String username;

    private String surname;

    private String phone;

    private String email;

    private String pass;

    private Date registrationDate;

    private Role role;

}
