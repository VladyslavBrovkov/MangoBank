package com.example.mangobank.service.impl;

import com.example.mangobank.models.entity.RootUsers;
import com.example.mangobank.repository.RootUsersRepository;
import com.example.mangobank.service.RootUsersService;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class RootUsersServiceImpl implements RootUsersService {

    private final RootUsersRepository rootUsersRepository;

    public RootUsersServiceImpl(RootUsersRepository rootUsersRepository) {
        this.rootUsersRepository = rootUsersRepository;
    }

    @Override
    public RootUsers createRootUser(RootUsers newRootUsers) {
        if (checkByEmailAndFirstName(newRootUsers) == false){
            return rootUsersRepository.save(newRootUsers);
        } else{
            throw new EntityExistsException("Such RootUser already exists");
        }
    }

    @Override
    public void deleteRootUser(RootUsers rootUsers) {
        if (!rootUsersRepository.findById(rootUsers.getId()).isPresent()){
            throw new EntityNotFoundException("No such user id database");
        }
        rootUsersRepository.delete(rootUsers);
    }

    @Override
    public RootUsers updateRootUser(RootUsers rootUsers) {
        if (!rootUsersRepository.findById(rootUsers.getId()).isPresent()){
            throw new EntityNotFoundException("No such user id database");
        }
        RootUsers rootUserFromDB = rootUsersRepository.findById(rootUsers.getId()).get();
        rootUserFromDB.setFirstName(rootUsers.getFirstName());
        rootUserFromDB.setLastName(rootUsers.getLastName());
        rootUserFromDB.setEmail(rootUsers.getEmail());
        rootUserFromDB.setRole(rootUsers.getRole());
        rootUserFromDB.setPassword(rootUsers.getPassword());
        return rootUsersRepository.save(rootUserFromDB);
    }

    @Override
    public List<RootUsers> getAllRootUsers() {
        return rootUsersRepository.findAll();
    }

    @Override
    public boolean checkByEmailAndFirstName(RootUsers rootUsers) {
        List<RootUsers> foundUsers = rootUsersRepository.findAll()
                .stream().filter(u -> u.getEmail().trim().equals(rootUsers.getEmail().trim())
                        && u.getFirstName().trim().equals(rootUsers.getFirstName().trim()))
                .collect(Collectors.toList());
        if (foundUsers.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
