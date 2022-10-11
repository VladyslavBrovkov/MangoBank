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
import java.util.Optional;

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
        if (loginDataRepository.findExistByEmail(userDtoRequest.getLoginEmail())) {
            User user = repository.getUserById(repository.getIdByEmail(userDtoRequest.getLoginEmail()));
            user = userDtoRequest.updateUserInfo(user);
            return repository.save(user);
        }
        else {
            throw new EntityNotFoundException("No such user for update");
        }
    }

    @Override
    public void updateUserLoginData(LoginDataDto loginDataDto) {
        if (loginDataRepository.findExistBySecretWord(loginDataDto.getOldSecretWord())) {
            Long loginDataId = loginDataRepository.getIdBySecretWord(loginDataDto.getOldSecretWord());
            LoginData loginData = loginDataRepository.findById(loginDataId).get();
            loginData.setLoginEmail(loginDataDto.getNewLoginEmail());
            loginData.setPassword(loginDataDto.getNewPassword());
            loginData.setSecretWord(loginDataDto.getNewSecretWord());
            loginDataRepository.save(loginData);
        } else {
            throw new EntityNotFoundException("No user with such loginData");
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
        if (user != null) {
            return UserDtoResponse.from(user);
        } else {
            throw new EntityNotFoundException("No user with such id");
        }
    }
}