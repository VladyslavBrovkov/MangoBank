package com.example.mangobank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MangoBankApplication implements CommandLineRunner {

    private final DemoDataCreation demoDataCreation;

    public MangoBankApplication(DemoDataCreation demoDataCreation) {
        this.demoDataCreation = demoDataCreation;
    }

    public static void main(String[] args) {
        SpringApplication.run(MangoBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        demoDataCreation.createDemoData();
    }
}
