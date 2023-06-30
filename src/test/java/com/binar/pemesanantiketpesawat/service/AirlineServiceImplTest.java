package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.AirlineRequest;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.model.Seat;
import com.binar.pemesanantiketpesawat.repository.AirlineRepository;
import com.binar.pemesanantiketpesawat.service.serviceImpl.AirlineServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirlineServiceImplTest {

    @InjectMocks
    private AirlineServiceImpl airlineService;

    @Mock
    private AirlineRepository airlineRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchByAirlineCode_ExistingCode_ShouldReturnAirline() {
        String airlineCode = "GA-455";
        Airline expectedAirline = new Airline();
        expectedAirline.setAirlineId(1);
        expectedAirline.setAirlineTimeFk(1);
        expectedAirline.setAirlineName("Garuda Indonesia");
        expectedAirline.setAirlineCode(airlineCode);
        expectedAirline.setDepartureGate("Gate A");
        expectedAirline.setArrivalGate("Gate B");
        List<Seat> flightClass = Arrays.asList(new Seat(), new Seat());
        expectedAirline.setFlightClass(flightClass);
        expectedAirline.setCreatedAt(LocalDateTime.now());
        expectedAirline.setModifiedAt(LocalDateTime.now());
        when(airlineRepository.findByAirlineCode(airlineCode)).thenReturn(expectedAirline);

        Airline actualAirline = airlineService.searchByAirlineCode(airlineCode);

        assertEquals(expectedAirline, actualAirline);
        verify(airlineRepository, times(1)).findByAirlineCode(airlineCode);
    }

    @Test
    void testSearchByAirlineCode_NonExistingCode_ShouldReturnNull() {
        String airlineCode = "QG";
        when(airlineRepository.findByAirlineCode(airlineCode)).thenReturn(null);

        Airline actualAirline = airlineService.searchByAirlineCode(airlineCode);

        assertNull(actualAirline);
        verify(airlineRepository, times(1)).findByAirlineCode(airlineCode);
    }

    @Test
    void testGetAllAirline() {
        Airline airline1 = new Airline();
        airline1.setAirlineId(1);
        airline1.setAirlineTimeFk(1);
        airline1.setAirlineName("Citilink");
        airline1.setAirlineCode("QG");
        airline1.setDepartureGate("Gate A");
        airline1.setArrivalGate("Gate B");
        List<Seat> flightClass1 = Arrays.asList(new Seat(), new Seat());
        airline1.setFlightClass(flightClass1);
        airline1.setCreatedAt(LocalDateTime.now());
        airline1.setModifiedAt(LocalDateTime.now());

        Airline airline2 = new Airline();
        airline2.setAirlineId(2);
        airline2.setAirlineTimeFk(2);
        airline2.setAirlineName("Garuda Indonesia");
        airline2.setAirlineCode("GA");
        airline2.setDepartureGate("Gate X");
        airline2.setArrivalGate("Gate Y");
        List<Seat> flightClass2 = Arrays.asList(new Seat(), new Seat(), new Seat());
        airline2.setFlightClass(flightClass2);
        airline2.setCreatedAt(LocalDateTime.now());
        airline2.setModifiedAt(LocalDateTime.now());

        List<Airline> expectedAirlines = Arrays.asList(airline1, airline2);
        when(airlineRepository.findAll()).thenReturn(expectedAirlines);

        List<Airline> actualAirlines = airlineService.getAllAirline();

        assertEquals(expectedAirlines, actualAirlines);
        verify(airlineRepository, times(1)).findAll();
    }

    @Test
    void testAddNewAirline_Success() {
        AirlineRequest airlineRequest = new AirlineRequest();
        airlineRequest.setAirlineTimeFk(1);
        airlineRequest.setAirlineName("British Airways");
        airlineRequest.setAirlineCode("BA");
        airlineRequest.setDepartureGate("Gate A");
        airlineRequest.setArrivalGate("Gate B");
        List<Seat> flightClass = Arrays.asList(new Seat(), new Seat());
        airlineRequest.setFlightClass(flightClass);

        when(airlineRepository.existsByAirlineCode(airlineRequest.getAirlineCode())).thenReturn(false);
        when(airlineRepository.save(any(Airline.class))).thenReturn(new Airline());

        Airline addedAirline = airlineService.addNewAirline(airlineRequest);

        assertNotNull(addedAirline);
        verify(airlineRepository, times(1)).existsByAirlineCode(airlineRequest.getAirlineCode());
        verify(airlineRepository, times(1)).save(any(Airline.class));
    }

    @Test
    void testAddNewAirline_DuplicateCode_ShouldReturnNull() {
        AirlineRequest airlineRequest = new AirlineRequest();
        airlineRequest.setAirlineTimeFk(2);
        airlineRequest.setAirlineName("British Airways");
        airlineRequest.setAirlineCode("BA");
        airlineRequest.setDepartureGate("Gate A");
        airlineRequest.setArrivalGate("Gate B");
        List<Seat> flightClass = Arrays.asList(new Seat(), new Seat(), new Seat());
        airlineRequest.setFlightClass(flightClass);

        when(airlineRepository.existsByAirlineCode(airlineRequest.getAirlineCode())).thenReturn(true);

        Airline addedAirline = airlineService.addNewAirline(airlineRequest);

        assertNull(addedAirline);
        verify(airlineRepository, times(1)).existsByAirlineCode(airlineRequest.getAirlineCode());
        verify(airlineRepository, never()).save(any(Airline.class));
    }
}
