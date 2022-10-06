package com.example.mangobank.service.utils;

public final class NumberGenerator {
    private String generatedCardNumber;
    private String generatedIbanNumber;


    public NumberGenerator() {
        this.generatedCardNumber = cardNumberGenerator();
        this.generatedIbanNumber = ibanGenerator();
    }

    public String getGeneratedCardNumber() {
        return generatedCardNumber;
    }

    public String getGeneratedIbanNumber() {
        return generatedIbanNumber;
    }

    private String cardNumberGenerator() {
        long a = (long) (Math.random() * (24000000 + 1)) + 27000000;
        long b = (long) (Math.random() * (37000000 + 1)) + 47000000;
        return a/10000 + " " + a/10000 + " " + b/10000 + " " + b%10000;
    }

    private String ibanGenerator() {
        long a = (long) (Math.random() * (14000000 + 1)) + 17000000;
        long b = (long) (Math.random() * (17000000 + 1)) + 19000000;
        return "UA" + "00" + a + b + a;
    }
}
