package com.example.mangobank.service;

import com.example.mangobank.models.entity.RootUsers;

import java.util.List;

public interface RootUsersService {
    RootUsers createRootUser(RootUsers newRootUsers);

    void deleteRootUser(RootUsers rootUsers);

    RootUsers updateRootUser(RootUsers rootUsers);

    List<RootUsers> getAllRootUsers();

    boolean checkByEmailAndFirstName(RootUsers rootUsers);
}
