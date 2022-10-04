package com.example.mangobank.model.dto;

import com.example.mangobank.enumerated.Currency;
import com.example.mangobank.model.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtoResponse {

    private Long id;

    private String iban;

    private String cardNumber;

    private Currency currency;

    private BigDecimal balance;

    public static AccountDtoResponse from(Account account) {
        AccountDtoResponse accountDtoResponse = new AccountDtoResponse();
        accountDtoResponse.setId(account.getId());
        accountDtoResponse.setIban(account.getIban());
        accountDtoResponse.setCardNumber(account.getCardNumber());
        accountDtoResponse.setCurrency(account.getCurrency());
        accountDtoResponse.setBalance(account.getBalance());
        return accountDtoResponse;
    }

}
