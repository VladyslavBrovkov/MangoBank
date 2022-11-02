package com.example.mangobank.controller;

import com.example.mangobank.model.dto.LoginDataDto;
import com.example.mangobank.model.dto.UserDtoRequest;
import com.example.mangobank.model.dto.UserDtoResponse;
import com.example.mangobank.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }


    @GetMapping("/getAll") //admin
    public List<UserDtoResponse> getAllUsers() {
        return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Validated @RequestBody UserDtoRequest userDtoRequest) {
        service.addUser(userDtoRequest);
        return new ResponseEntity<>("User successfully added", HttpStatus.OK);
    }

    @DeleteMapping("/delete") //admin
    public ResponseEntity<String> deleteUser(@Validated @RequestBody UserDtoRequest userDtoRequest) {
        service.deleteUser(userDtoRequest);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUserInfo(@Validated @RequestBody UserDtoRequest userDtoRequest) {
        service.updateUserInfo(userDtoRequest);
        return new ResponseEntity<>("User info successfully updated", HttpStatus.OK);
    }

    @PutMapping("/updateLogin")
    public ResponseEntity<String> updateUserLoginData(@Validated @RequestBody LoginDataDto loginDataDto) {
        service.updateUserLoginData(loginDataDto);
        return new ResponseEntity<>("User loginData successfully updated", HttpStatus.OK);
    }

    @GetMapping("/details/{user_id}")
    public UserDtoResponse getUserDetails(@PathVariable Long user_id) {
        return service.getUserById(user_id);
    }
}
