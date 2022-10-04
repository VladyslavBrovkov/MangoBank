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

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addUser(User user) {
        if (!findUserByEmail(user.getLoginData().getLoginEmail())) {
            user.setRegistrationDate(new Date());
            repository.save(user);
        } else {
            throw new EntityExistsException("User already exists");
        }
    }

    @Override
    public void deleteUser(User user) {
        if (findUserByEmail(user.getLoginData().getLoginEmail())) {
            Long userId = getIdByEmail(user.getLoginData().getLoginEmail());
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
    public boolean findUserByEmail(String email) {
        List<User> list = repository.findAll().
                stream().filter(c -> c.getLoginData().getLoginEmail().equals(email)).toList();
        return list.size() > 0;
    }

    @Override
    public boolean findUserByPhone(String phone) {
        List<User> list = repository.findAll().
                stream().filter(c -> c.getPhone().equals(phone)).toList();
        return list.size() > 0;
    }

    @Override
    public User updateUserInfo(User user) {  //todo: make full update
        if (findUserByPhone(user.getPhone())) {
            User userFromDb = repository.findById(getIdByPhone(user.getPhone())).get();
            userFromDb.setPhone(user.getPhone());
            userFromDb.setFirstName(user.getFirstName());
            userFromDb.setLastName(user.getLastName());
            return repository.save(userFromDb);
        } else {
            throw new EntityNotFoundException("Nu such user for update");
        }
    }

    @Override
    public List<UserDtoResponse> getAll() {
        return repository.findAll().stream()
                .map(UserDtoResponse::from).toList();
    }

    @Override
    public Long getIdByEmail(String email) {
        List<User> list = repository.findAll().
                stream().filter(c -> c.getLoginData().getLoginEmail().equals(email)).toList();
        if (list.size() > 0) {
            return list.get(0).getId();
        } else {
            throw new EntityNotFoundException("No user with such Email");
        }
    }

    @Override
    public Long getIdByPhone(String phone) {
        List<User> list = repository.findAll().
                stream().filter(c -> c.getPhone().equals(phone)).toList();
        if (list.size() > 0) {
            return list.get(0).getId();
        } else {
            throw new EntityNotFoundException("No user with such Phone");
        }
    }
}
