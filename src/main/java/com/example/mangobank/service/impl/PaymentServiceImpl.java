package com.example.mangobank.service.impl;

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
import java.util.Collections;
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
    public void createPaymentWithIban(PaymentDto paymentDto) {
        Account accountFrom = accountRepository.findByIban(paymentDto.getFromIban())
                .orElseThrow(() -> new EntityNotFoundException("No fromAcc with such IBAN"));
        Account accountTo = accountRepository.findByIban(paymentDto.getToIban())
                .orElseThrow(() -> new EntityNotFoundException("No toAcc with such IBAN"));
        Payment payment = paymentDto.fromByIban(paymentDto);
        accountFrom.setBalance(accountFrom.getBalance().subtract(payment.getAmount()));
        accountTo.setBalance(accountTo.getBalance().subtract(payment.getAmount()));
        payment.setFromAccount(accountFrom);
        payment.setToAccount(accountTo);
        paymentRepository.save(payment);
    }


    @Transactional
    @Override
    public void createPaymentWithCardNumber(PaymentDto payment) {

    }

    @Override
    public List<Payment> findByAmount(BigDecimal amountFrom, BigDecimal amountTo) {
        return paymentRepository.findAll().stream().filter(p -> p.getAmount().compareTo(amountFrom) > 0
                        && p.getAmount().compareTo(amountTo) < 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findByDate(Date dateFrom, Date dateTo) {
        if (dateFrom.after(dateTo)){
            return Collections.emptyList();
        }
        return paymentRepository.getAllBetweenDates(dateFrom,dateTo);
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
