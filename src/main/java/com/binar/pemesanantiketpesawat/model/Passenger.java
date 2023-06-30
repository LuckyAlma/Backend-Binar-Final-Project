package com.binar.pemesanantiketpesawat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penumpang_id ")
    private Integer passengerId;
    @Column (name ="title")
    private String title;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "family_name")
    private String familyName;
    @Column(name = "date_of_birth")
    private Date dob;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "identity_number")
    private Long identityNumber;
    @Column(name = "identity_issuing_country")
    private String identityIssuingCountry; //
    @Column(name = "expired_at")
    private Date expiredAt;

    public Passenger(String title, String fullName, String familyName, Date bod, String nationality, Long identityNumber, String identityIssuingCountry, Date expiredAt) {
        this.title = title;
        this.fullName = fullName;
        this.familyName = familyName;
        this.dob = bod;
        this.nationality = nationality;
        this.identityNumber = identityNumber;
        this.identityIssuingCountry = identityIssuingCountry;
        this.expiredAt = expiredAt;
    }
}
