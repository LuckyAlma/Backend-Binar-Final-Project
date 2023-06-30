package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

    Boolean existsByAirportIATA(String IATAResponse);

    @Query(value = "SELECT a.airportLocation FROM Airport a")
    List<String> showAirportLocationOnly();

    Airport findByAirportLocation(String airportRequest);

}
