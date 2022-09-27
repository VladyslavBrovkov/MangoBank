package com.example.mangobank.controller.rest;

import com.example.mangobank.model.dto.ClientDto;
import com.example.mangobank.model.entity.Client;
import com.example.mangobank.service.impl.ClientServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class UserController {
    private final ClientServiceImpl clientService;

    public UserController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDto> getAllClients() {
        List<Client> clientList = clientService.getAll();
        List<ClientDto> dtoList = new ArrayList<>();
        for (Client cl : clientList) {
            dtoList.add(Client.fromClientToDto(cl));
        }
        return dtoList;
    }
}
