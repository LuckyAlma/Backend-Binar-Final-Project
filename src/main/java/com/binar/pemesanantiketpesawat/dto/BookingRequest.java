package com.binar.pemesanantiketpesawat.dto;

import com.binar.pemesanantiketpesawat.model.Customers;
import com.binar.pemesanantiketpesawat.model.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BookingRequest {

    private UUID uuidUser;
    private String airlineCode;
    private String flightClass;
    private Integer adult;
    private Integer child;
    private Integer baby;
    private Customers customers;
    private List<Passenger> passengers;

}
