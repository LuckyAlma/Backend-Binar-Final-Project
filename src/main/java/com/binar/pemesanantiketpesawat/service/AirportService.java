package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.AirportRequest;
import com.binar.pemesanantiketpesawat.model.Airport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AirportService {

    Airport addNewAirport(AirportRequest airportRequest);
    List<String> getAllAirport();
    List<String> getDepartureAirport();
    List<String> getArrivalAirportFromDeparture(String departureRequest);
}
