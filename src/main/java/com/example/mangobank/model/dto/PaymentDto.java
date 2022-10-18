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

    private String fromCard; //todo can we create transfer from Iban to card? or just from Iban to Iban, from card to card?

    private String toCard;

    private BigDecimal sumOfPayment;

    private Currency currencyOfPayment;

    public static Payment fromByIban(PaymentDto paymentDto){ //todo should be named 'to()' , the idea main class is Dto, so method to() convert class to Entity,
        // the method from() convert from entity to the dto class. And use this naming convention in all dto-s.
        Payment payment = new Payment();
        payment.setPaymentTime(new Date());
        payment.setSumOfPayment(paymentDto.getSumOfPayment());
        payment.setCurrencyOfPayment(Currency.UAH);
        return payment;
    }

}
