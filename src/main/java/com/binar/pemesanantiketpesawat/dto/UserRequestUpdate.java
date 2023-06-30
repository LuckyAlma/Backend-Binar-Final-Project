package com.binar.pemesanantiketpesawat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserRequestUpdate {
    UUID uuidUser;
    String name;
    String phoneNumber;
    String password;
}
