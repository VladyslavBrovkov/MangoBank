package com.example.mangobank.service;

import com.example.mangobank.model.dto.PaymentDto;
import com.example.mangobank.model.entity.Payment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PaymentService {
    void createPaymentWithIban(PaymentDto payment);

    void createPaymentWithCardNumber(PaymentDto payment);

    List<Payment> findByAmount(BigDecimal sumFrom, BigDecimal sumTo);

    List<Payment> findByDate(Date dateFrom, Date dateTo);

    void deletePaymentById(Long id);

    List<Payment> getAllIncomingPaymentsByAccountId(Long accountId);

    List<Payment> getAllOutcomingPaymentsByAccountId(Long accountId);

    List<Payment> getPaymentsByUserId(Long userId);

    List<Payment> getAllPayments();
}
