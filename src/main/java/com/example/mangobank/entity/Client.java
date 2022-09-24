package com.example.mangobank.entity;

import com.sun.istack.NotNull;
import jdk.jfr.Timestamp;
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

    @OneToOne
    private Account account;

    private String name;

    private String surname;

    private String phone;

    private Date registrationDate;

    private Role role;

}
