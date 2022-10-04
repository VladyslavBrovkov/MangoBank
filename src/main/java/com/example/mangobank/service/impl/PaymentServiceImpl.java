package com.example.mangobank.service.impl;

import com.example.mangobank.model.entity.Payment;
import com.example.mangobank.repository.AccountRepository;
import com.example.mangobank.repository.PaymentRepository;
import com.example.mangobank.repository.UserRepository;
import com.example.mangobank.service.PaymentService;
import org.springframework.stereotype.Service;

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

    @Override
    public void createPayment(Payment payment) {
        paymentRepository.save(payment);
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
    public void deletePayment(Long id) {
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

        //todo use similar way for optional or get
///        return account.get().orElseThrow()

        if (account.isPresent()) {//todo refactor with using optional aproach with lambda
            return paymentRepository.findByToAccount(account.get());
        } else {
            throw new EntityNotFoundException("No such account");
        }
    }

    @Override
    public List<Payment> getAllOutcomingPaymentsByAccountId(Long accountId) {
        var account = accountRepository.findById(accountId);
        if (account.isPresent()) { //todo refactor
            return paymentRepository.findByFromAccount(account.get());
        } else {
            throw new EntityNotFoundException("No such account");
        }
    }

    @Override
    public List<Payment> getPaymentsByUserId(Long userId) {
        var user = userRepository.findById(userId);
        List<Payment> userPayments = new ArrayList<>();
        if (user.isPresent()) { //todo uncomment  fix as paymentRepository.findByUser();
//            user.get().getAccount().forEach(a -> userPayments.addAll(a.getToAccountPayment()));//todo: refactor
//            user.get().getAccount().forEach(a -> userPayments.addAll(a.getFromAccountPayment()));
        }
        return userPayments;
    }


    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
