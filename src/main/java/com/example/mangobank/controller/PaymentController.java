package com.example.mangobank.controller;

import com.example.mangobank.model.dto.PaymentDto;
import com.example.mangobank.service.impl.PaymentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentServiceImpl paymentService;

    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/createPaymentBetweenAccounts")
    public ResponseEntity<String> createPayment(@RequestBody PaymentDto paymentDto) {
        paymentService.createPaymentWithIban(paymentDto);
        return new ResponseEntity<>("Created successfully", HttpStatus.OK);
    }

    @PostMapping("/putMoneyOnMyAccount")
    public ResponseEntity<String> putMoneyToAccount(@Validated @RequestBody PaymentDto paymentDto) {
        return new ResponseEntity<>("Money on account, success", HttpStatus.OK);
    }

}
