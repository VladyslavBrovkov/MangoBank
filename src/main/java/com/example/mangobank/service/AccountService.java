package com.example.mangobank.service;


import com.example.mangobank.models.entity.Account;

import java.util.List;

public interface AccountService {
    void addAccount(Account account);

    void deleteAccount(Account account);

    void deleteAccountById(Long id);

    boolean findAccountByIban(String iban);

    Account updateAccount(Account account);

    List<Account> getAll();
}
