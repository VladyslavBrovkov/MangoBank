package com.example.mangobank.controller;

import com.example.mangobank.model.dto.AccountDtoRequest;
import com.example.mangobank.model.dto.AccountDtoResponse;
import com.example.mangobank.model.dto.UserDtoRequest;
import com.example.mangobank.model.dto.UserDtoResponse;
import com.example.mangobank.model.entity.Account;
import com.example.mangobank.service.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountServiceImpl service;

    public AccountController(AccountServiceImpl accountService) {
        this.service = accountService;
    }

    @GetMapping("/getAll")
    public List<AccountDtoResponse> getAllAccounts() {
        return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Validated @RequestBody AccountDtoRequest accountDtoRequest){
        service.addAccount(accountDtoRequest);
        return new ResponseEntity<>("Account successfully added", HttpStatus.OK);
    }

}
