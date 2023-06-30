package com.binar.pemesanantiketpesawat.request;

import lombok.Data;

import java.util.UUID;

@Data
public class Token {

    private String token;
    private UUID uuidUser;

}
