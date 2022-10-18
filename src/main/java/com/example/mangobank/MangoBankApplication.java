package com.example.mangobank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MangoBankApplication implements CommandLineRunner {
    @Autowired //todo try to move binding of beans to the constructor without using @Autowired
    private DemoDataCreation demoDataCreation;

    public static void main(String[] args) {
        SpringApplication.run(MangoBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        demoDataCreation.demoDataCreation();
    }
}
//todo please create a README.md file)))