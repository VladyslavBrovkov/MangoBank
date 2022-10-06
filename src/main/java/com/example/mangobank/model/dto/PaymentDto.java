package com.example.mangobank.model.dto;

import com.example.mangobank.enumerated.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Long id;

    private Date paymentTime;

    private String fromIBAN;

    private String toIBAN;

    private String fromCard;

    private String toCard;

    private BigDecimal sumOfPayment;

    private Currency currencyOfPayment;
}
