package com.example.mangobank.models.entity;

import com.example.mangobank.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "client")
    private List<Account> account;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String password;

    private Date registrationDate;

    @Enumerated(EnumType.STRING)
    private static final Role role = Role.CLIENT;

}
