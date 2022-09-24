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

    @OneToOne
    private Account sender;

    @OneToOne
    private Account receiver;

    private Date paymentTime;

    private BigDecimal paymentSum;

    private boolean status;

}
