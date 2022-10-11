package com.example.mangobank.service.impl;

import com.example.mangobank.model.dto.AccountDto;
import com.example.mangobank.model.entity.Account;
import com.example.mangobank.model.entity.User;
import com.example.mangobank.repository.AccountRepository;
import com.example.mangobank.repository.UserRepository;
import com.example.mangobank.service.AccountService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with such id was not found"));
        Account account = AccountDto.to();
        account.setUser(user);
        accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account was not found"));
        accountRepository.deleteById(account.getId());
    }


    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream()
                .map(AccountDto::from).collect(Collectors.toList());
    }
}
