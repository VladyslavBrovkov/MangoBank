package com.example.mangobank.service.impl;

import com.example.mangobank.enumerated.Currency;
import com.example.mangobank.model.dto.PaymentDto;
import com.example.mangobank.model.entity.Account;
import com.example.mangobank.model.entity.Payment;
import com.example.mangobank.repository.AccountRepository;
import com.example.mangobank.repository.PaymentRepository;
import com.example.mangobank.repository.UserRepository;
import com.example.mangobank.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, AccountRepository accountRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void createPaymentWithIban(PaymentDto payment) {
        Account fromAcc = accountRepository.findByIban(payment.getFromIban());
        Account toAcc = accountRepository.findByIban(payment.getToIban());
//        if (accountFrom.isPresent() && accountTo.isPresent()) {
        Payment paymentToInit = new Payment();
        fromAcc.setBalance(fromAcc.getBalance().subtract(payment.getSumOfPayment()));
        toAcc.setBalance(toAcc.getBalance().add(payment.getSumOfPayment()));
        paymentToInit.setFromAccount(fromAcc);
        paymentToInit.setToAccount(toAcc);
        paymentToInit.setCurrencyOfPayment(Currency.UAH);
        paymentToInit.setSumOfPayment(payment.getSumOfPayment());
        paymentToInit.setPaymentTime(new Date());
        accountRepository.save(fromAcc);
        accountRepository.save(toAcc);
        paymentRepository.save(paymentToInit);
//        }
    }


    @Transactional
    @Override
    public void createPaymentWithCardNumber(PaymentDto payment) {

    }

    @Override
    public List<Payment> findPaymentsBySum(BigDecimal sum) {
        return paymentRepository.findAll().stream().filter(p -> p.getSumOfPayment().equals(sum))
                .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findPaymentsByDate(Date date) {
        return paymentRepository.findAll().stream().filter(p -> p.getPaymentTime().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePaymentById(Long id) {
        var payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.delete(payment.get());
        } else {
            throw new EntityNotFoundException("No such payment");
        }
    }

    @Override
    public List<Payment> getAllIncomingPaymentsByAccountId(Long accountId) {
        var account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get().getToAccountPayment();
        } else {
            throw new EntityNotFoundException("No such account");
        }
    }

    @Override
    public List<Payment> getAllOutcomingPaymentsByAccountId(Long accountId) {
        var account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get().getFromAccountPayment();
        } else {
            throw new EntityNotFoundException("No such account");
        }
    }

    @Override
    public List<Payment> getPaymentsByUserId(Long userId) {
        var user = userRepository.findById(userId);
        List<Payment> userPayments = new ArrayList<>();
        if (user.isPresent()) {
            user.get().getAccount().forEach(a -> userPayments.addAll(a.getToAccountPayment()));//todo: refactor
            user.get().getAccount().forEach(a -> userPayments.addAll(a.getFromAccountPayment()));
        }
        return userPayments;
    }


    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
