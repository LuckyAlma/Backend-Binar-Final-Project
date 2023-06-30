package com.binar.pemesanantiketpesawat.request;

import lombok.Data;

import java.sql.Date;

@Data
public class PassengerRequest {

    private String title;
    private String fullName;
    private String familyName;
    private Date dob;
    private String nationality;
    private Long identityNumber;
    private String identityIssuingCountry;
    private Date expiredAt;

}