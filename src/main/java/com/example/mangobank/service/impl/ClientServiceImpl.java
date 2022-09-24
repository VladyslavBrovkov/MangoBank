package com.example.mangobank.service.impl;

import com.example.mangobank.entity.Client;
import com.example.mangobank.repository.ClientRepository;
import com.example.mangobank.service.ClientService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void addClient(Client client) {
        if (getClientByEmail(client.getEmail()) == null) {
            clientRepository.save(client);
        } else {
            throw new EntityExistsException("Client already exists");
        }
    }

    @Override
    public void deleteClient(Client client) {
        if (getClientByEmail(client.getEmail()) != null) {
            clientRepository.delete(client);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void deleteClientById(Long id) {
        if (clientRepository.findById(id).isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Client getClientByEmail(String email) {
        List<Client> list = clientRepository.findAll().
                stream().filter(c -> c.getEmail().equals(email)).collect(Collectors.toList());
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Client updateClient(Client client) {
        if (getClientByEmail(client.getEmail()) != null) {
            Client client1 = getClientByEmail(client.getEmail());
            client1.setUsername(client.getUsername());
            client1.setSurname(client.getSurname());
            client1.setEmail(client.getEmail());
            return client1;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }
}
