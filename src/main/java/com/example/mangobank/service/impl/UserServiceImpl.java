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

    private final LoginDataRepository loginDataRepository; //todo check if it needed here?


    public UserServiceImpl(UserRepository repository, LoginDataRepository loginDataRepository) {
        this.repository = repository;
        this.loginDataRepository = loginDataRepository;
    }

    @Override
    public void addUser(UserDtoRequest userDtoRequest) { //todo do we really need to use var? this is just a question, if it is good practice we can leave this
        if (!repository.existByEmail(userDtoRequest.getLoginEmail())) { //todo use userDtoRequest here
            var user = UserDtoRequest.to(userDtoRequest);
            user.setRegistrationDate(new Date());
            repository.save(user);
        } else {
            throw new EntityExistsException("User already exists");
        }
    }

    @Override
    public void deleteUser(UserDtoRequest userDtoRequest) {
        var user = UserDtoRequest.to(userDtoRequest);
        if (repository.existByEmail((user.getLoginData().getLoginEmail()))) {
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
        if (repository.existByPhone(user.getPhone())) {
            Long userId = repository.getIdByPhone(user.getPhone());
            User userFromDb = repository.findById(userId).get(); //todo check this get without ifPresent
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
        //var user = UserDtoRequest.to(userDtoRequest); //todo remove
        User user = repository.findByEmail(userDtoRequest.getLoginEmail()); //todo it is better obtain optional here

        if (user != null) {
            //todo implement save loginData
            //user.setLoginData(loginData); //todo probably not needed
            //var loginData = user.getLoginData(); //todo why we use var
            //return repository.save(userFromDb); //todo I think we can do not update User, we will just update LoginData entity
            return user;
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
        return UserDtoResponse.from(repository.getUserById(id));
    }

}
