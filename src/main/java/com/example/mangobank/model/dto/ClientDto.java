package com.example.mangobank.model.dto;

import lombok.Data;

@Data
public class ClientDto {
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private String clientPhone;
    private String clientEmail;
    private String clientRegistrationDate;
    private String clientRole;
}
