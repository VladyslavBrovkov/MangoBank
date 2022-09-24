package com.example.mangobank.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.bytebuddy.asm.Advice;

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

    @OneToOne
    private Client client;

    private String IBAN;

    private BigDecimal withdraw;

    private Date lastTransactionTime;


}
