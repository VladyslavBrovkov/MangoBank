package com.example.mangobank.service.impl;

import com.example.mangobank.model.dto.AccountDtoRequest;
import com.example.mangobank.model.dto.AccountDtoResponse;
import com.example.mangobank.model.entity.Account;
import com.example.mangobank.repository.AccountRepository;
import com.example.mangobank.repository.UserRepository;
import com.example.mangobank.service.AccountService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
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
    public void addAccount(AccountDtoRequest account) {
        var dbAccount = AccountDtoRequest.to(account);
        if (!findAccountByIban(dbAccount.getIban())) {
            var user = userRepository.findById(account.getUserId());
            if (user.isPresent()) {
                dbAccount.setUser(user.get());
                accountRepository.save(dbAccount);
            } else {
                throw new EntityNotFoundException("No such User in db");
            }
        } else {
            throw new EntityExistsException("Account with such IBAN already exists");
        }
    }

    @Override
    public void deleteAccountById(Long id) {
        var account = accountRepository.findById(id);
        if (account.isPresent()) {
            accountRepository.delete(account.get());
        } else {
            throw new EntityNotFoundException("No account with such ID");
        }
    }

    @Override
    public boolean findAccountByIban(String iban) {
        List<Account> finalList = accountRepository.findAll().stream()
                .filter(a -> a.getIban().trim().equals(iban.trim())).toList();
        return !finalList.isEmpty();
    }

    @Override
    public Account updateAccount(Account account) {
        Long id = account.getId();
        if (findAccountByIban(account.getIban())) {
            var dbAccount = accountRepository.findById(id).get();
            dbAccount.setIban(account.getIban());
            dbAccount.setBalance(account.getBalance());
            dbAccount.setToAccountPayment(account.getToAccountPayment());
            dbAccount.setFromAccountPayment(account.getFromAccountPayment());
            dbAccount.setIban(account.getIban());
            dbAccount.setUser(account.getUser());
            dbAccount.setCardNumber(account.getCardNumber());
            return accountRepository.save(dbAccount);
        } else {
            throw new EntityNotFoundException("No such account");
        }
    }

    @Override
    public List<AccountDtoResponse> getAll() {
        return accountRepository.findAll().stream().map(a -> AccountDtoResponse.from(a)).collect(Collectors.toList());
    }
}
