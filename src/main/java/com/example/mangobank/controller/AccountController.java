package com.example.mangobank.controller;

import com.example.mangobank.service.impl.AccountServiceImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }
}
