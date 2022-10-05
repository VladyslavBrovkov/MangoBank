package com.example.mangobank.model.dto;

import com.example.mangobank.enumerated.Currency;
import com.example.mangobank.model.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtoRequest { //todo think probably we don't need 2 dtos (Request Response) for Account and Payment, we only need to have for user in places where we use password
    private Long userId;
    private Currency currency;

    public static Account to(AccountDtoRequest accountDtoRequest){
        Account account = new Account();
        account.setToAccountPayment(new ArrayList<>());
        account.setFromAccountPayment(new ArrayList<>());
        account.setBalance(BigDecimal.valueOf(0.0));
        account.setCurrency(accountDtoRequest.getCurrency());
        account.setCardNumber(cardNumberGenerator());
        account.setIban(ibanGenerator());
        return account;
    }

    public static String cardNumberGenerator() {
        long a = (long) (Math.random() * (11000000 + 1)) + 14000000; //todo we mustn't place any logic to dto classes, prerable place logic to service or entity, check every dto
        long b = (long) (Math.random() * (15000000 + 1)) + 19000000; //todo read about POJO classes
        return String.valueOf(a/10000 + " " + a%10000 + " " + b/10000 + " " + b%10000); //todo don't need use String.valueOf
    }

    public static String ibanGenerator() {
        long a = (long) (Math.random() * (11000000 + 1)) + 14000000;
        long b = (long) (Math.random() * (15000000 + 1)) + 19000000;
        return String.valueOf("UA" + "00" + a + a+1 + b); //todo don't need use String.valueOf
    }

}
