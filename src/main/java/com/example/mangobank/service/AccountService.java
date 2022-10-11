package com.example.mangobank.service;


import com.example.mangobank.model.dto.AccountDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    void createAccount(Long userId);

    void deleteAccountById(Long id);

    void putMoneyOnAccount(AccountDto accountDto);

    List<AccountDto> getAll();
}
