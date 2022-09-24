package com.example.mangobank.service;

import com.example.mangobank.entity.Client;

import java.util.List;

public interface ClientService {
    void addClient(Client client);

    void deleteClient(Client client);

    void deleteClientById(Long id);

    Client getClientByEmail(String email);

    Client updateClient(Client client);

    List<Client> getAll();

}
