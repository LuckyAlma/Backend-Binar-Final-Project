package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
    Boolean existsByAirlineCode(String theCode);
    List<Airline> findByAirlineTimeFk(Integer airlineTimeFk);
    Airline findByAirlineCode(String airlineCode);

}
