package com.example.mangobank.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;

    @OneToMany(mappedBy = "fromAccount", cascade = CascadeType.ALL)
    private List<Payment> outPayment;

    @OneToMany(mappedBy = "toAccount", cascade = CascadeType.ALL)
    private List<Payment> inPayment;

    private String IBAN;

    private Currency currency;

    private BigDecimal withdraw;

    private Date lastTransactionTime;


}
