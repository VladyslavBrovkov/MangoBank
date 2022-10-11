package com.example.mangobank.controller;

import com.example.mangobank.model.dto.PaymentDto;
import com.example.mangobank.model.entity.Payment;
import com.example.mangobank.repository.PaymentRepository;
import com.example.mangobank.service.impl.PaymentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentServiceImpl paymentService;

    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestBody PaymentDto paymentDto) {
        paymentService.createPaymentWithIban(paymentDto);
        return new ResponseEntity<>("Created successfully", HttpStatus.OK);
    }


}
