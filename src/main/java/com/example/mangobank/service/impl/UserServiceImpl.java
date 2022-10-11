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
        if (!loginDataRepository.existByEmail(userDtoRequest.getLoginEmail())) {
            User user = UserDtoRequest.to(userDtoRequest); //todo: read about var +-
            user.setRegistrationDate(new Date());
            repository.save(user);
        } else {
            throw new EntityExistsException("User already exists");
        }
    }

    @Override
    public void deleteUser(UserDtoRequest userDto) {
        User user = repository.findByEmail(userDto.getLoginEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        repository.delete(user);
    }

//        userOpt.ifPresentOrElse(repository::delete, //todo: refactor
//            () -> new EntityNotFoundException("No such user"));
//        userOpt.map(o -> repository.deleteById(o.getId()))
//            .orElseThrow(MyCustomException::new);

    @Override
    public void deleteUserById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No user with such id");
        }
    }

    @Override
    public User updateUserInfo(UserDtoRequest userDto) {
        User user = repository.findByEmail(userDto.getLoginEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return repository.save(userDto.updateUserInfo(user));
    }


    @Override
    public void updateUserLoginData(LoginDataDto loginDataDto) {  //todo: check exist + find in Repo
        if (loginDataRepository.existBySecretWord(loginDataDto.getOldSecretWord())) {
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