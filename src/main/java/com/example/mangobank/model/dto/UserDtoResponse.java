package com.example.mangobank.model.dto;

import com.example.mangobank.enumerated.Role;
import com.example.mangobank.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private Date registrationDate; //todo add property email to UserDtoResponse
    private Role role;

    public static UserDtoResponse from(User user) {
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setId(user.getId());
        userDtoResponse.setPhone(user.getPhone());
        userDtoResponse.setFirstName(user.getFirstName());
        userDtoResponse.setLastName(user.getLastName());
        userDtoResponse.setRegistrationDate(user.getRegistrationDate());
        userDtoResponse.setRole(user.getRole());
        return userDtoResponse;
    }
}
