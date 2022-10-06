package com.example.mangobank.service.impl;

import com.example.mangobank.model.dto.UserDtoRequest;
import com.example.mangobank.model.dto.UserDtoResponse;
import com.example.mangobank.model.entity.User;
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



    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addUser(UserDtoRequest userDtoRequest) {

        if (!repository.existByEmail(userDtoRequest.getLoginEmail())) {
            var user = UserDtoRequest.to(userDtoRequest);
            user.setRegistrationDate(new Date());
            repository.save(user);
        } else {
            throw new EntityExistsException("User already exists");
        }
    }

    @Override
    public void deleteUser(UserDtoRequest userDtoRequest) {

        if (repository.existByEmail((userDtoRequest.getLoginEmail()))) {
            var user = UserDtoRequest.to(userDtoRequest);
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
        Optional<User> userOtp = repository.findByEmail(userDtoRequest.getLoginEmail());
        //todo use this approach get data in one call to DB!  we must not creat 2 calls to db 1- repository.getIdByPhone(user.getPhone()); 2 - User userFromDb = repository.findById(userId).get();
        if (userOtp.isPresent()) {
            User user = userOtp.get();
            userDtoRequest.to(user); //todo use this approach
            //var loginData = user.getLoginData(); //todo think do we need chage login data in method updateUserInfo()
            //Long userId = repository.getIdByEmail(loginData.getLoginEmail());
            //User userFromDb = repository.findById(userId).get();
            //userFromDb.setLoginData(loginData);
            return repository.save(user);
        } else {
            throw new EntityNotFoundException("Nu such user for update");
        }

//        var user = UserDtoRequest.to(userDtoRequest);
//        if (repository.findExistByPhone(user.getPhone())) {
//            Long userId = repository.getIdByPhone(user.getPhone());
//            User userFromDb = repository.findById(userId).get();
//            userFromDb.setPhone(user.getPhone());
//            userFromDb.setFirstName(user.getFirstName());
//            userFromDb.setLastName(user.getLastName());
//            return repository.save(userFromDb);
//        } else {
//            throw new EntityNotFoundException("Nu such user for update");
//        }
    }

    @Override
    public User updateUserLoginData(UserDtoRequest userDtoRequest) {

        if ( repository.existByEmail(userDtoRequest.getLoginEmail())) { //todo try to use repository.findByEmail() approach as in example in updateUserInfo()
            var user = UserDtoRequest.to(userDtoRequest);
            var loginData = user.getLoginData();
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
        if (user != null) {
            return UserDtoResponse.from(user);
        } else {
            throw new EntityNotFoundException("No user with such id");
        }
    }
}