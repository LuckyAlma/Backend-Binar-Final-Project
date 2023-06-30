package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.AirportRequest;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Airport;
import com.binar.pemesanantiketpesawat.service.AirportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AirportControllerTest {

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAirport_Success() {
        List<String> airportList = Arrays.asList("Soekarno-Hatta", "I Gusti Ngurah Rai");

        when(airportService.getAllAirport()).thenReturn(airportList);

        ResponseEntity<MessageModel> response = airportController.getAllAirport();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success to get airport", response.getBody().getMessage());
        assertEquals(airportList, response.getBody().getData());

        verify(airportService, times(1)).getAllAirport();
    }

    @Test
    void testGetAllAirport_Failure() {
        when(airportService.getAllAirport()).thenReturn(null);

        ResponseEntity<MessageModel> response = airportController.getAllAirport();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to get airport", response.getBody().getMessage());
        assertNull(response.getBody().getData());

        verify(airportService, times(1)).getAllAirport();
    }

    @Test
    void testGetDepartureAirport_Success() {
        List<String> airportList = Arrays.asList("Soekarno-Hatta", "I Gusti Ngurah Rai");

        when(airportService.getDepartureAirport()).thenReturn(airportList);

        ResponseEntity<MessageModel> response = airportController.getDepartureAirport();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success to get airport", response.getBody().getMessage());
        assertEquals(airportList, response.getBody().getData());

        verify(airportService, times(1)).getDepartureAirport();
    }

    @Test
    void testGetDepartureAirport_Failure() {
        when(airportService.getDepartureAirport()).thenReturn(null);

        ResponseEntity<MessageModel> response = airportController.getDepartureAirport();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(airportService, times(1)).getDepartureAirport();
    }


    @Test
    void testGetArrivalAirport_Success() {
        List<String> airportList = Arrays.asList("Soekarno-Hatta", "I Gusti Ngurah Rai");
        String departureRequest = "Departure Airport";

        when(airportService.getArrivalAirportFromDeparture(any(String.class))).thenReturn(airportList);

        ResponseEntity<MessageModel> response = airportController.getDepartureAirport(departureRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success to get airport", response.getBody().getMessage());
        assertEquals(airportList, response.getBody().getData());

        verify(airportService, times(1)).getArrivalAirportFromDeparture(departureRequest);
    }

    @Test
    void testGetArrivalAirport_Failure() {
        String departureRequest = "Departure Airport";

        when(airportService.getArrivalAirportFromDeparture(any(String.class))).thenReturn(null);

        ResponseEntity<MessageModel> response = airportController.getDepartureAirport(departureRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody()); // Perubahan ini
        verify(airportService, times(1)).getArrivalAirportFromDeparture(departureRequest);
    }



    @Test
    void testAddNewAirport_Success() {
        AirportRequest airportRequest = new AirportRequest("Cengkareng", "Jakarta", "CGK", "Soekarno-Hatta");
        Airport airport = new Airport();
        airport.setAirportId(1);
        airport.setAirportName("Airport 1");

        when(airportService.addNewAirport(any(AirportRequest.class))).thenReturn(airport);

        ResponseEntity<MessageModel> response = airportController.addNewAirport(airportRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Add new airport", response.getBody().getMessage());
        assertEquals(airport, response.getBody().getData());

        verify(airportService, times(1)).addNewAirport(airportRequest);
    }

    @Test
    void testAddNewAirport_Failure() {
        AirportRequest airportRequest = new AirportRequest("Cengkareng", "Jakarta", "CGK", "Soekarno-Hatta");

        when(airportService.addNewAirport(any(AirportRequest.class))).thenReturn(null);

        ResponseEntity<MessageModel> response = airportController.addNewAirport(airportRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to add airport", response.getBody().getMessage());
        assertNull(response.getBody().getData());

        verify(airportService, times(1)).addNewAirport(airportRequest);
    }
}
