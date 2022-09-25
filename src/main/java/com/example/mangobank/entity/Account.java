package com.example.mangobank.entity;

import com.example.mangobank.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany (mappedBy = "toAccount")
    private List<Payments> toAccountPayment;

    @OneToMany (mappedBy = "fromAccount")
    private List<Payments> fromAccountPayment;


    @Column(name = "IBAN")
    private String iban;

    private String cardNumber;

    private Currency currency;

    private BigDecimal balance;

}
