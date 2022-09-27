package com.example.mangobank.models.entity;

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
    private List<Payment> toAccountPayment;

    @OneToMany (mappedBy = "fromAccount")
    private List<Payment> fromAccountPayment;


    @Column(name = "IBAN")
    private String iban;

    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal balance;

}
