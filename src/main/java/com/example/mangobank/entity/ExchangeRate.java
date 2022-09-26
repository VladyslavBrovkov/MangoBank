package com.example.mangobank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class ExchangeRate { //todo if history need to be saved - entity, if not - model
    private BigDecimal buyRate;
    private BigDecimal saleRate;
}
