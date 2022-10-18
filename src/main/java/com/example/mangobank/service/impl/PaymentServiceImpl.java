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
import java.util.Optional;
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
        Account fromAcc = accountRepository.findByIban(paymentDto.getFromIban()) //todo better to name as accountFrom, accountTo
                .orElseThrow(() -> new EntityNotFoundException("No fromAcc with such IBAN"));
        Account toAcc = accountRepository.findByIban(paymentDto.getToIban())
                .orElseThrow(() -> new EntityNotFoundException("No toAcc with such IBAN"));
        Payment payment = PaymentDto.fromByIban(paymentDto); //todo we can use non-static method here, because we have object paymentDto
        fromAcc.setBalance(fromAcc.getBalance().subtract(payment.getSumOfPayment())); //todo create methods in Account class topUp(BigDecimal sum), and reduce(BigDecimal sum), or subtract, increase, increment, decrement
        toAcc.setBalance(toAcc.getBalance().subtract(payment.getSumOfPayment()));
        payment.setFromAccount(fromAcc);
        payment.setToAccount(toAcc);
        paymentRepository.save(payment);
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
