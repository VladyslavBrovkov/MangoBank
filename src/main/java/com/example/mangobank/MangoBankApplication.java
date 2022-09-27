package com.example.mangobank;

import com.example.mangobank.enums.Currency;
import com.example.mangobank.models.entity.Account;
import com.example.mangobank.models.entity.Client;
import com.example.mangobank.models.entity.RootUser;
import com.example.mangobank.service.impl.AccountServiceImpl;
import com.example.mangobank.service.impl.ClientServiceImpl;
import com.example.mangobank.service.impl.PaymentServiceImpl;
import com.example.mangobank.service.impl.RootUserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

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
        RootUser admin = new RootUser();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setPassword("admin");
        admin.setPhone("0931111111");
        rootUserService.createRootUser(admin);
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(0.0));
        account.setCurrency(Currency.UAH);
        account.setIban("UA1234567891011121");
        account.setCardNumber("5555 5555 5555 5555");
        Client client = new Client();
        client.setFirstName("client1");
        client.setLastName("client1");
        client.setEmail("client1@gmail.com");
        client.setPassword("client1");
        client.setPhone("0672222222");
        client.setAccount(List.of(account));
        account.setClient(client);
        clientService.addClient(client);
        accountService.addAccount(account);
    }
}
