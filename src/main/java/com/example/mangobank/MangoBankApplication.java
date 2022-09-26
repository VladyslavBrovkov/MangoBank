package com.example.mangobank;

import com.example.mangobank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//        cl1.setUsername("Vlad");
//        cl1.setSurname("Brovkov");
//        Account ac1 = new Account();
//        ac1.setCurrency(Currency.UAH);
//        ac1.setIban("UA123123123123");
//        Payment p1 = new Payment();
//        p1.setFromAccount(ac1);
//        p1.setToAccount(ac1);
//        p1.setSumOfPayment(BigDecimal.TEN);
//        p1.setPaymentTime(new Date());
//        ac1.setClient(cl1);
//        cl1.setAccount(List.of(ac1));
//        clientsRepository.save(cl1);

    }
}
