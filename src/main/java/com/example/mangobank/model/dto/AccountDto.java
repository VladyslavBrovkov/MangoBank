package com.example.mangobank.model.dto;

import com.example.mangobank.enumerated.Currency;
import com.example.mangobank.model.entity.Account;
import com.example.mangobank.service.utils.NumberGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;

    private Long userId;

    private String iban;

    private String cardNumber;

    private Currency currency;

    private BigDecimal balance;


    public static AccountDto from(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUserId(account.getUser().getId());
        accountDto.setIban(account.getIban());
        accountDto.setCardNumber(account.getCardNumber());
        accountDto.setCurrency(account.getCurrency());
        accountDto.setBalance(account.getBalance());
        return accountDto;
    }

    public static Account to(){
        Account account = new Account();
        NumberGenerator nb = new NumberGenerator();
        account.setToAccountPayment(new ArrayList<>());
        account.setFromAccountPayment(new ArrayList<>());
        account.setBalance(BigDecimal.valueOf(0.0));
        account.setCurrency(Currency.UAH);
        account.setCardNumber(nb.getGeneratedCardNumber());
        account.setIban(nb.getGeneratedIbanNumber());
        return account;
    }

}
