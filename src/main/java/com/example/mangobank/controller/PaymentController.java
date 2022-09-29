package com.example.mangobank.controller;

import com.example.mangobank.repository.PaymentRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}
