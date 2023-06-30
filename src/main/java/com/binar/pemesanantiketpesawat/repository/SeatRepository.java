package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    @Query("SELECT f FROM Seat f WHERE f.airlineCodeFk = :airlineCode AND f.flightClass = :flightClass")
    Seat findByAirlineCodeFkAndFlightClassSingleData(@Param("airlineCode") Integer airlineCode, @Param("flightClass") String flightClass);
    List<Seat> findByAirlineCodeFkAndFlightClass(Integer airlineCode, String flightClass);
}
