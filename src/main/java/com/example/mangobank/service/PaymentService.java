package com.example.mangobank.service;

import com.example.mangobank.model.dto.PaymentDto;
import com.example.mangobank.model.entity.Payment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PaymentService {
    void createPayment(PaymentDto payment);

    List<Payment> findPaymentsBySum(BigDecimal sum);

    List<Payment> findPaymentsByDate(Date date);

    void deletePaymentById(Long id);

    List<Payment> getAllIncomingPaymentsByAccountId(Long accountId);

    List<Payment> getAllOutcomingPaymentsByAccountId(Long accountId);

    List<Payment> getPaymentsByUserId(Long userId);

    List<Payment> getAllPayments();
}
