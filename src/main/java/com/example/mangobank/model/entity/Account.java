package com.example.mangobank.model.entity;

import com.example.mangobank.enumerated.Currency;
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
    @JoinColumn(name = "user_id")
    private User user;

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
