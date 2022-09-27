package com.example.mangobank.service.impl;

import com.example.mangobank.models.entity.Payment;
import com.example.mangobank.repository.AccountsRepository;
import com.example.mangobank.repository.PaymentRepository;
import com.example.mangobank.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final AccountsRepository accountsRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, AccountsRepository accountsRepository) {
        this.paymentRepository = paymentRepository;
        this.accountsRepository = accountsRepository;
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
        if (payment.isPresent()){
            paymentRepository.delete(payment.get());
        } else {
            throw new EntityNotFoundException("No such payment");
        }
    }

    @Override
    public List<Payment> getAllIncomingPaymentsByAccountId(Long accountId) {
        var account = accountsRepository.findById(accountId);
        if (account.isPresent()){
            return account.get().getToAccountPayment();
        } else {
            throw new EntityNotFoundException("No such account");
        }
    }

    @Override
    public List<Payment> getAllOutcomingPaymentsByAccountId(Long accountId) {
        var account = accountsRepository.findById(accountId);
        if (account.isPresent()){
            return account.get().getFromAccountPayment();
        } else {
            throw new EntityNotFoundException("No such account");
        }
    }


    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
