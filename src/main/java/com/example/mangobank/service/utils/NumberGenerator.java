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
        String cardNumber = "";
        int a = 0;
        for (int i = 0; i < 16; i++) {
            a = (int) (Math.random() * (10 - 1) + 1);
            cardNumber += a;
        }
        return cardNumber;
    }

    private String ibanGenerator() {
        String ibanNumber = "UA";
        int a = 0;
        for (int i = 0; i < 26; i++) {
            a = (int) (Math.random() * (10 - 1) + 1);
            ibanNumber += a;
        }
        return ibanNumber;
    }
}
