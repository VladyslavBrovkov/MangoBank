package com.example.mangobank.service.impl;

import com.example.mangobank.model.entity.Account;
import com.example.mangobank.repository.AccountRepository;
import com.example.mangobank.service.AccountService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccount(Account account) {
        if (!findAccountByIban(account.getIban())) {
            accountRepository.save(account);
        } else {
            throw new EntityExistsException("Account with such IBAN already exists");
        }
    }

    @Override
    public void deleteAccount(Account account) {
        if (findAccountByIban(account.getIban())){
            accountRepository.delete(account);
        } else {
            throw new EntityNotFoundException("No such account");
        }
    }

    @Override
    public void deleteAccountById(Long id) {
        var account = accountRepository.findById(id);
        if (account.isPresent()){
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
        if (findAccountByIban(account.getIban())){
         var dbAccount = accountRepository.findById(id).get();
         dbAccount.setIban(account.getIban());
         dbAccount.setBalance(account.getBalance());
//         dbAccount.setToAccountPayment(account.getToAccountPayment()); //too checke and remove
//         dbAccount.setFromAccountPayment(account.getFromAccountPayment());
         dbAccount.setIban(account.getIban());
         dbAccount.setUser(account.getUser());
         dbAccount.setCardNumber(account.getCardNumber());
         return accountRepository.save(dbAccount);
        } else {
            throw new EntityNotFoundException("No such account");
        }
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
