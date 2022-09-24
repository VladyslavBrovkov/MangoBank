package com.example.mangobank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class ExchangeRate {
    private BigDecimal buyRate;
    private BigDecimal saleRate;
}
