package com.example.mangobank.service;


import com.example.mangobank.model.dto.AccountDto;

import java.util.List;

public interface AccountService {
    void createAccount(Long userId);

    void deleteAccountById(Long id);

    List<AccountDto> getAll();
}
