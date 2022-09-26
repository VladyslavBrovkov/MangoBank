package com.example.mangobank.models.entity;

import com.example.mangobank.models.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "payment_time")
    private Date paymentTime;

    @ManyToOne
    @JoinColumn(name = "fromAccountPayment")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "toAccountPayment")
    private Account toAccount;

    @Column(name = "payment_sum")
    private BigDecimal sumOfPayment;
}