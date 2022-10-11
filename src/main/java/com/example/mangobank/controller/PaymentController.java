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
//
//    @GetMapping("/getAll")
//    public List<Payment> getAllPayments() {
//        return paymentService.findAll();
//    }
//
//    @GetMapping("/account/from/{accountFromId}")
//    public List<Payment> getFromAccountPayments(@PathVariable Long accountFromId) {
//        return paymentService.findAll();
//    }
//
//    @GetMapping("/account/to/{accountToId}")
//    public List<Payment> getToAccountPayments(@PathVariable Long accountToId) {
//        return paymentRepository.findAll();
//    }
//
//    @GetMapping("/user/{userId}")
//    public List<Payment> getUserPayment(@PathVariable Long userId) {
//        return new ArrayList<>();
//    }

    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestBody PaymentDto paymentDto) {
        paymentService.createPaymentWithIban(paymentDto);
        return new ResponseEntity<>("Created successfully", HttpStatus.OK);
    }


}
