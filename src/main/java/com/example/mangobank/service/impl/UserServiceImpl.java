package com.example.mangobank.service.impl;

import com.example.mangobank.model.dto.LoginDataDto;
import com.example.mangobank.model.dto.UserDtoRequest;
import com.example.mangobank.model.dto.UserDtoResponse;
import com.example.mangobank.model.entity.LoginData;
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
    private final UserRepository userRepository;
    private final LoginDataRepository loginDataRepository;

    public UserServiceImpl(UserRepository repository, LoginDataRepository loginDataRepository) {
        this.userRepository = repository;
        this.loginDataRepository = loginDataRepository;
    }

    @Override
    public void addUser(UserDtoRequest userDtoRequest) {
        if (loginDataRepository.findByEmail(userDtoRequest.getLoginEmail()).isEmpty()) {
            User user = UserDtoRequest.to(userDtoRequest);
            user.setRegistrationDate(new Date());
            userRepository.save(user);
        } else {
            throw new EntityExistsException("User already exists");
        }
    }

    @Override
    public void deleteUser(UserDtoRequest userDto) {
        User user = userRepository.findByEmail(userDto.getLoginEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user with such id"));
        userRepository.deleteById(user.getId());
    }

    @Override
    public User updateUserInfo(UserDtoRequest userDto) {
        User user = userRepository.findByEmail(userDto.getLoginEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userRepository.save(userDto.updateUserInfo(user));
    }

    @Override
    public LoginData updateUserLoginData(LoginDataDto loginDataDto) {
        LoginData loginData = loginDataRepository.findBySecretWord(loginDataDto.getOldSecretWord())
                .orElseThrow(() -> new EntityNotFoundException("Incorrect secret word"));
        return loginDataRepository.save(loginDataDto.updateLoginData(loginData));
    }

    @Override
    public List<UserDtoResponse> getAll() {
        return userRepository.findAll().stream()
                .map(UserDtoResponse::from).toList();
    }

    @Override
    public UserDtoResponse getUserById(Long id) {
        User user = userRepository.getUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with such id was not found"));
        return UserDtoResponse.from(user);
    }
}