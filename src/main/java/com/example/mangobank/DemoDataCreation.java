package com.example.mangobank;

import com.example.mangobank.enumerated.Role;
import com.example.mangobank.model.dto.UserDtoRequest;
import com.example.mangobank.service.impl.AccountServiceImpl;
import com.example.mangobank.service.impl.PaymentServiceImpl;
import com.example.mangobank.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DemoDataCreation {

    private final UserServiceImpl userService;

    private final AccountServiceImpl accountService;

    private final PaymentServiceImpl paymentService;

    public DemoDataCreation(UserServiceImpl userService, AccountServiceImpl accountService, PaymentServiceImpl paymentService) {
        this.userService = userService;
        this.accountService = accountService;
        this.paymentService = paymentService;
    }


    public void demoDataCreation() { //todo naming convention method name is verb, initializeDemoData or createDemoData
        UserDtoRequest admin = new UserDtoRequest();
        admin.setFirstName("ADMIN");
        admin.setLastName("ADMIN");
        admin.setPhone("+380671112233");
        admin.setRole(Role.ADMIN);
        admin.setPassword("$2a$12$wEEk9mqlfeIQcf2s7feTB.bDzYmpowGRiMyc/pkTgaAXFRfjxPuj2");
        admin.setLoginEmail("admin@gmail.com");
        admin.setSecretWord("admin");
        UserDtoRequest user1 = new UserDtoRequest();
        user1.setFirstName("USER1");
        user1.setLastName("USER1");
        user1.setPhone("+380672223344");
        user1.setPassword("$2a$12$1R071nSH2m3Aad28zbGK9uJUfWULmqLp2odRBikKzv9YAozotyQ.6");
        user1.setLoginEmail("user1@gmail.com");
        user1.setSecretWord("user1");
        UserDtoRequest user2 = new UserDtoRequest();
        user2.setFirstName("USER2");
        user2.setLastName("USER2");
        user2.setPhone("+380673334455");
        user2.setPassword("$2a$12$rKP5c1ZI4NjSaWs01qubV.dI0ImtX46xeX.OSkeHmigNdOzWEJ3ue");
        user2.setLoginEmail("user2@gmail.com");
        user2.setSecretWord("user2");
        UserDtoRequest user3 = new UserDtoRequest();
        user3.setFirstName("USER3");
        user3.setLastName("USER3");
        user3.setPhone("+380674445566");
        user3.setPassword("$2a$12$G4xVQb11xD72zNsNjZ1cmOLcEoNnKf2fm0LfOK2LkAPY10aA2Rg0e");
        user3.setLoginEmail("user3@gmail.com");
        user3.setSecretWord("user3");
        userService.addUser(admin);
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        accountService.createAccount(1L);
        accountService.createAccount(2L);
        accountService.createAccount(4L);
        accountService.createAccount(2L);
        accountService.createAccount(1L);
    }

}
