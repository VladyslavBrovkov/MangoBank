package com.example.mangobank.service.impl;

import com.example.mangobank.model.entity.Account;
import com.example.mangobank.repository.AccountsRepository;
import com.example.mangobank.service.AccountService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountsRepository accountsRepository;

    public AccountServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public void addAccount(Account account) {
        if (findAccountByIban(account.getIban()) == false) {
            accountsRepository.save(account);
        } else {
            throw new EntityExistsException("Account with such IBAN already exists");
        }
    }

    @Override
    public void deleteAccount(Account account) {
        if (findAccountByIban(account.getIban()) == true){
            accountsRepository.delete(account);
        } else {
            throw new EntityNotFoundException("No such account");
        }
    }

    @Override
    public void deleteAccountById(Long id) {
        var account = accountsRepository.findById(id);
        if (account.isPresent()){
            accountsRepository.delete(account.get());
        } else {
            throw new EntityNotFoundException("No account with such ID");
        }
    }

    @Override
    public boolean findAccountByIban(String iban) {
        List<Account> finalList = accountsRepository.findAll().stream()
                .filter(a -> a.getIban().trim().equals(iban.trim())).collect(Collectors.toList());
        if (finalList.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public Account updateAccount(Account account) {
        Long id = account.getId();
        if (findAccountByIban(account.getIban()) == true){
         var dbAccount = accountsRepository.findById(id).get();
         dbAccount.setIban(account.getIban());
         dbAccount.setBalance(account.getBalance());
         dbAccount.setToAccountPayment(account.getToAccountPayment());
         dbAccount.setFromAccountPayment(account.getFromAccountPayment());
         dbAccount.setIban(account.getIban());
         dbAccount.setClient(account.getClient());
         dbAccount.setCardNumber(account.getCardNumber());
         return accountsRepository.save(dbAccount);
        } else {
            throw new EntityNotFoundException("No such account");
        }
    }

    @Override
    public List<Account> getAll() {
        return accountsRepository.findAll();
    }
}
