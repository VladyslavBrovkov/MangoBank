package com.example.mangobank.service;


import com.example.mangobank.model.dto.AccountDtoRequest;
import com.example.mangobank.model.dto.AccountDtoResponse;
import com.example.mangobank.model.entity.Account;

import java.util.List;

public interface AccountService {
    void addAccount(AccountDtoRequest account);

    void deleteAccountById(Long id);

    boolean findAccountByIban(String iban);

    Account updateAccount(Account account);

    List<AccountDtoResponse> getAll();
}
