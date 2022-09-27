package com.example.mangobank;

import com.example.mangobank.repository.ClientRepository;
import com.example.mangobank.service.impl.AccountServiceImpl;
import com.example.mangobank.service.impl.ClientServiceImpl;
import com.example.mangobank.service.impl.PaymentServiceImpl;
import com.example.mangobank.service.impl.RootUserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MangoBankApplication implements CommandLineRunner {

    private final ClientServiceImpl clientService;
    private final AccountServiceImpl accountService;
    private final PaymentServiceImpl paymentService;
    private final RootUserServiceImpl rootUserService;

    public MangoBankApplication(ClientServiceImpl clientService, AccountServiceImpl accountService, PaymentServiceImpl paymentService, RootUserServiceImpl rootUserService) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.paymentService = paymentService;
        this.rootUserService = rootUserService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MangoBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
