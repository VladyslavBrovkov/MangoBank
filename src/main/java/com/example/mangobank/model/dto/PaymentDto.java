package com.example.mangobank.model.dto;

import com.example.mangobank.enumerated.Currency;
import com.example.mangobank.model.entity.Payment;
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

    private String fromIban;

    private String toIban;

    private String fromCard;

    private String toCard;

    private BigDecimal sumOfPayment;

    private Currency currencyOfPayment;

    public Payment fromByIban(PaymentDto paymentDto){
        Payment payment = new Payment();
        payment.setPaymentTime(new Date());
        payment.setAmount(paymentDto.getSumOfPayment());
        payment.setCurrencyOfPayment(Currency.UAH);
        return payment;
    }

}
