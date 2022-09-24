package com.example.mangobank.entity;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    private Date paymentTime;

    private BigDecimal paymentSum;

    private boolean status;

}
