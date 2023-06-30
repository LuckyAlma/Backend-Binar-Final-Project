package com.binar.pemesanantiketpesawat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "airport_id", nullable = true)
    private Integer airportId;
    @Column(name = "airport_location", nullable = false)
    private String airportLocation;
    @Column(name = "airport_province", nullable = false)
    private String airportProvince;
    @Column(name = "airport_IATA", nullable = false, length = 3)
    private String airportIATA;
    @Column(name = "airport_name", nullable = false)
    private String airportName;

    public Airport(String airportIATA, String airportName) {
        this.airportIATA = airportIATA;
        this.airportName = airportName;
    }
}
