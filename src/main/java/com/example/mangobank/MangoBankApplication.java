package com.example.mangobank;

import com.example.mangobank.entity.*;
import com.example.mangobank.enums.Currency;
import com.example.mangobank.enums.Role;
import com.example.mangobank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class MangoBankApplication implements CommandLineRunner {
    @Autowired
    private ClientRepository clientRepository;

    public static void main(String[] args) {
        SpringApplication.run(MangoBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Client cl1 = new Client();
//        Account ac1 = new Account();
//        ac1.setClient(cl1);
//        ac1.setCurrency(Currency.UAH);
//        ac1.setIban("UA123123123123123");
//        ac1.setLastTransactionTime(new Date());
//        ac1.setWithdraw(BigDecimal.TEN);
//        cl1.setUsername("Vladyslav");
//        cl1.setSurname("Brovkov");
//        cl1.setEmail("brovkov@gmail.com");
//        cl1.setPass("qwerty");
//        cl1.setRole(Role.ADMIN);
//        cl1.setPhone("380931111111");
//        cl1.setRegistrationDate(new Date());
//        cl1.setAccount(ac1);
//        clientRepository.save(cl1);
    }
}
