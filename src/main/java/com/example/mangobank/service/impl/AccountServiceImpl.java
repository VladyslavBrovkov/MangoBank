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
    public void createAccount(Long userId) { //todo can be named like create(), or createByUserId() Account is redundant because it is AccountService
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with such id was not found"));
        Account account = AccountDto.to();
        account.setUser(user);
        accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(Long id) { //todo can be named like delete(), Account is redundant because it is AccountService, 'byId' is also redundant
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account was not found"));
        accountRepository.deleteById(account.getId());
    }


    @Override
    public List<AccountDto> getAll() {  //todo move in all places to the begin of the class (order for crud: 1 get, 2 create 3 update 4 delete
        return accountRepository.findAll().stream()
                .map(AccountDto::from).collect(Collectors.toList());
    }
}
