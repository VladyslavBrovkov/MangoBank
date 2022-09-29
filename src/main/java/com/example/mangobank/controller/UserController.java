package com.example.mangobank.controller;

import com.example.mangobank.model.dto.UserDto;
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
    public List<UserDto> getAllClients() {
        return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Validated @RequestBody UserDto userDto){
         service.addUser(UserDto.to(userDto));
         return new ResponseEntity<>("User successfully added", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@Validated @RequestBody UserDto userDto){
        service.deleteUser(UserDto.to(userDto));
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateUser(@Validated @RequestBody UserDto userDto){
        service.updateUserInfo(UserDto.to(userDto));
        return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
    }
}
