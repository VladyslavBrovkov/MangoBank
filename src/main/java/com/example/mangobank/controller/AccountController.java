package com.example.mangobank.controller;

import com.example.mangobank.model.dto.AccountDto;
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

    public AccountController(AccountServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<AccountDto> getAllAccounts() {
        return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@Validated @RequestBody AccountDto accountDto){
        service.addAccount(accountDto);
        return new ResponseEntity<>("Account successfully added", HttpStatus.OK);
    }

}
