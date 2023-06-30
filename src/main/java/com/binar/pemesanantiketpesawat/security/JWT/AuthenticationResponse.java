package com.binar.pemesanantiketpesawat.security.JWT;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AuthenticationResponse {
    private Long id;
    private UUID uuidUser;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private List<String> roles;
    private String jwt;

    public AuthenticationResponse(Long id, UUID uuidUser, String name, String email, String password, String phoneNumber, List<String> roles, String jwt) {
        this.id = id;
        this.uuidUser = uuidUser;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.jwt = jwt;
    }
}
