package com.example.mangobank.controller;

import com.example.mangobank.model.dto.UserDtoRequest;
import com.example.mangobank.model.dto.UserDtoResponse;
import com.example.mangobank.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<UserDtoResponse> getAllClients() {
        return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Validated @RequestBody UserDtoRequest userDtoRequest){
         service.addUser(UserDtoRequest.to(userDtoRequest));
         return new ResponseEntity<>("User successfully added", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@Validated @RequestBody UserDtoRequest userDtoRequest){
        service.deleteUser(UserDtoRequest.to(userDtoRequest));
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateUser(@Validated @RequestBody UserDtoRequest userDtoRequest){
        service.updateUserInfo(UserDtoRequest.to(userDtoRequest));
        return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
    }

    @GetMapping("/details/{user_id}") //todo: refactor
    public ResponseEntity<String> getAllClients(@PathVariable Long user_id) {

        return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
    }
}
