package com.binar.pemesanantiketpesawat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirportRequest {
    private String airportLocation;
    private String airportProvince;
    private String airportIATA;
    private String airportName;
}
