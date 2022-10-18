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
    public ResponseEntity<String> createAccount(@Validated @RequestBody AccountDto accountDto) {
        service.createAccount(accountDto.getUserId());
        return new ResponseEntity<>("Account successfully added", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{account_id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long account_id) {
        service.deleteAccountById(account_id);
        return new ResponseEntity<>("Account successfully deleted", HttpStatus.OK);
    }

}
