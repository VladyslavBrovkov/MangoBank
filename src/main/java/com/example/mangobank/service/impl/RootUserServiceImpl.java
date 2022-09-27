package com.example.mangobank.service.impl;

import com.example.mangobank.model.entity.RootUser;
import com.example.mangobank.repository.RootUserRepository;
import com.example.mangobank.service.RootUserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RootUserServiceImpl implements RootUserService {

    private final RootUserRepository rootUserRepository;

    public RootUserServiceImpl(RootUserRepository rootUserRepository) {
        this.rootUserRepository = rootUserRepository;
    }

    @Override
    public RootUser createRootUser(RootUser newRootUser) {
        if (checkByFirstName(newRootUser) == false){
            return rootUserRepository.save(newRootUser);
        } else{
            throw new EntityExistsException("Such RootUser already exists");
        }
    }

    @Override
    public void deleteRootUser(RootUser rootUsers) {
        if (!rootUserRepository.findById(rootUsers.getId()).isPresent()){
            throw new EntityNotFoundException("No such user id database");
        }
        rootUserRepository.delete(rootUsers);
    }

    @Override
    public RootUser updateRootUser(RootUser rootUsers) {
        if (!rootUserRepository.findById(rootUsers.getId()).isPresent()){
            throw new EntityNotFoundException("No such user id database");
        }
        RootUser rootUserFromDB = rootUserRepository.findById(rootUsers.getId()).get();
        rootUserFromDB.setFirstName(rootUsers.getFirstName());
        rootUserFromDB.setLastName(rootUsers.getLastName());
        rootUserFromDB.setPhone(rootUsers.getPhone());
        rootUserFromDB.setPassword(rootUsers.getPassword());
        return rootUserRepository.save(rootUserFromDB);
    }

    @Override
    public List<RootUser> getAllRootUsers() {
        return rootUserRepository.findAll();
    }

    @Override
    public boolean checkByFirstName(RootUser rootUser) {
        List<RootUser> foundUsers = rootUserRepository.findAll()
                .stream().filter(u -> u.getFirstName().trim().equals(rootUser.getFirstName().trim()))
                .collect(Collectors.toList());
        if (foundUsers.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
