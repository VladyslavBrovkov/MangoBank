package com.example.mangobank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDtoRequest {
    private String fromCard;
    private String toCard;
    private String fromIban;
    private String toIban;
    private BigDecimal sum;

}
