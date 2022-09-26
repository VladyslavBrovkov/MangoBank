package com.example.mangobank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "IBAN")
    private String iban;

    private String cardNumber;

    private Currency currency;

    private BigDecimal withdraw;

    private Date lastTransactionTime; //todo why this field needed?


}
