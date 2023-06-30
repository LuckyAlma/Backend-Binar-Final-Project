package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.AirlineRequest;
import com.binar.pemesanantiketpesawat.model.Airline;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AirlineService {

    Airline searchByAirlineCode(String codeRequest);
    List<Airline> getAllAirline();
    Airline addNewAirline(AirlineRequest airlineRequest);

}
