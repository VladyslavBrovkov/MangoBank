package com.example.mangobank;

import com.example.mangobank.entity.*;
import com.example.mangobank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class MangoBankApplication implements CommandLineRunner {
    @Autowired
    private ClientRepository clientRepository;

    public static void main(String[] args) {
        SpringApplication.run(MangoBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Payment p1 = new Payment();
        Client cl1 = new Client();
        Account ac1 = new Account();
        ac1.setClient(cl1);
        ac1.setCurrency(Currency.UAH);
        ac1.setIBAN("UA123123123123123");
        ac1.setLastTransactionTime(new Date());
        ac1.setWithdraw(BigDecimal.TEN);
        Account ac2 = new Account();
        ac1.setClient(cl1);
        ac1.setCurrency(Currency.UAH);
        ac1.setIBAN("UA123123123123123");
        ac1.setLastTransactionTime(new Date());
        ac1.setWithdraw(BigDecimal.TEN);
        p1.setFromAccount(ac1);
        p1.setToAccount(ac2);
        p1.setPaymentSum(BigDecimal.ONE);
        p1.setStatus(true);
        List<Account> list1 = new ArrayList<>();
        list1.add(ac1);
        list1.add(ac2);
        cl1.setUsername("Vladyslav");
        cl1.setSurname("Brovkov");
        cl1.setEmail("brovkov777@gmail.com");
        cl1.setPassword("qwerty");
        cl1.setRole(Role.ADMIN);
        cl1.setPhone("380931122745");
        cl1.setRegistrationDate(new Date());
        cl1.setAccount(list1);
        clientRepository.save(cl1);
    }
}
