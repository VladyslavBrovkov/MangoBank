package com.example.mangobank.model.entity;

import com.example.mangobank.enumerated.Role;
import com.example.mangobank.model.dto.ClientDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany(mappedBy = "client")
    private List<Account> account;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String password;

    private Date registrationDate;

    @Enumerated(EnumType.STRING)
    private static final Role role = Role.CLIENT;

    public static ClientDto fromClientToDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(client.getId());
        clientDto.setClientEmail(client.getEmail());
        clientDto.setClientPhone(client.getPhone());
        clientDto.setClientFirstName(client.getFirstName());
        clientDto.setClientLastName(client.getLastName());
        clientDto.setClientRegistrationDate(client.getRegistrationDate().toString());
        clientDto.setClientRole(Role.CLIENT.toString());
        return clientDto;
    }
}
