package com.example.mangobank.service;


import com.example.mangobank.model.dto.AccountDto;
import com.example.mangobank.model.entity.Account;

import java.util.List;

public interface AccountService {
    void addAccount(AccountDto account);

    void deleteAccountById(Long id);

    boolean findAccountByIban(String iban);

    Account updateAccount(Account account);

    List<AccountDto> getAll();
}
