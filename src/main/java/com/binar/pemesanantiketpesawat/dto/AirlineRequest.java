package com.binar.pemesanantiketpesawat.dto;


import com.binar.pemesanantiketpesawat.model.Seat;
import lombok.Data;

import java.util.List;

@Data
public class AirlineRequest {
    private Integer airlineTimeFk;
    private String airlineName;
    private String airlineCode;
    private String departureGate;
    private String arrivalGate;
    List<Seat> flightClass;
}
