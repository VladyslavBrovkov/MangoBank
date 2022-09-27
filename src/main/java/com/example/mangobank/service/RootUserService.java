package com.example.mangobank.service;

import com.example.mangobank.model.entity.RootUser;

import java.util.List;

public interface RootUserService {
    RootUser createRootUser(RootUser newRootUser);

    void deleteRootUser(RootUser rootUser);

    RootUser updateRootUser(RootUser rootUser);

    List<RootUser> getAllRootUsers();

    boolean checkByFirstName(RootUser rootUsers);
}
