package com.example.mangobank.service;

import com.example.mangobank.models.entity.Payment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PaymentService {
    void createPayment(Payment payment);

    List<Payment> findPaymentsBySum(BigDecimal sum);

    List<Payment> findPaymentsByDate(Date date);

    void deletePayment(Long id);

    List<Payment> getAllIncomingPaymentsByAccountId(Long accountId);

    List<Payment> getAllOutcomingPaymentsByAccountId(Long accountId);

    List<Payment> getPaymentsByClientId(Long clientId);

    List<Payment> getAllPayments();
}
