package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.AirlineRequest;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.repository.AirlineRepository;
import com.binar.pemesanantiketpesawat.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public Airline searchByAirlineCode(String codeRequest) {
        return airlineRepository.findByAirlineCode(codeRequest);
    }

    @Override
    public List<Airline> getAllAirline() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline addNewAirline(AirlineRequest airlineRequest) {

        if (airlineRepository.existsByAirlineCode(airlineRequest.getAirlineCode())) return null;

        return airlineRepository.save(
                new Airline(
                        airlineRequest.getAirlineTimeFk(),
                        airlineRequest.getAirlineName(),
                        airlineRequest.getAirlineCode(),
                        airlineRequest.getDepartureGate(),
                        airlineRequest.getArrivalGate(),
                        airlineRequest.getFlightClass()
                )
        );
    }
}
