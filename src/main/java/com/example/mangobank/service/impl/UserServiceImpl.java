package com.example.mangobank.service.impl;

import com.example.mangobank.model.dto.UserDtoRequest;
import com.example.mangobank.model.dto.UserDtoResponse;
import com.example.mangobank.model.entity.User;
import com.example.mangobank.repository.LoginDataRepository;
import com.example.mangobank.repository.UserRepository;
import com.example.mangobank.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final LoginDataRepository loginDataRepository;


    public UserServiceImpl(UserRepository repository, LoginDataRepository loginDataRepository) {
        this.repository = repository;
        this.loginDataRepository = loginDataRepository;
    }

    @Override
    public void addUser(UserDtoRequest userDtoRequest) {
        var user = UserDtoRequest.to(userDtoRequest);
        if (!loginDataRepository.findExistByEmail(user.getLoginData().getLoginEmail())) {
            user.setRegistrationDate(new Date());
            repository.save(user);
        } else {
            throw new EntityExistsException("User already exists");
        }
    }

    @Override
    public void deleteUser(UserDtoRequest userDtoRequest) {
        var user = UserDtoRequest.to(userDtoRequest);
        if (loginDataRepository.findExistByEmail((user.getLoginData().getLoginEmail()))) {
            Long userId = repository.getIdByEmail(user.getLoginData().getLoginEmail());
            repository.deleteById(userId);
        } else {
            throw new EntityNotFoundException("No such user");
        }
    }

    @Override
    public void deleteUserById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No user with such id");
        }
    }

    @Override
    public User updateUserInfo(UserDtoRequest userDtoRequest) {
        var user = UserDtoRequest.to(userDtoRequest);
        if (repository.findExistByPhone(user.getPhone())) {
            Long userId = repository.getIdByPhone(user.getPhone());
            User userFromDb = repository.findById(userId).get();
            userFromDb.setPhone(user.getPhone());
            userFromDb.setFirstName(user.getFirstName());
            userFromDb.setLastName(user.getLastName());
            return repository.save(userFromDb);
        } else {
            throw new EntityNotFoundException("Nu such user for update");
        }
    }

    @Override
    public User updateUserLoginData(UserDtoRequest userDtoRequest) {
        var user = UserDtoRequest.to(userDtoRequest);
        var loginData = user.getLoginData();
        if (loginDataRepository.findExistByEmail(loginData.getLoginEmail())) {
            Long userId = repository.getIdByEmail(loginData.getLoginEmail());
            User userFromDb = repository.findById(userId).get();
            userFromDb.setLoginData(loginData);
            return repository.save(userFromDb);
        } else {
            throw new EntityNotFoundException("Nu user with such Email");
        }
    }

    @Override
    public List<UserDtoResponse> getAll() {
        return repository.findAll().stream()
                .map(UserDtoResponse::from).toList();
    }

    @Override
    public UserDtoResponse getUserById(Long id) {
        var user = repository.getUserById(id);
        return UserDtoResponse.from(user);
    }

}
