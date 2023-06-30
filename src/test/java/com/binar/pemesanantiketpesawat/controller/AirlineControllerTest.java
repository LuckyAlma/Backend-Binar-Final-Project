package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.AirlineRequest;
import com.binar.pemesanantiketpesawat.dto.AirlineResponse;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.service.AirlineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AirlineControllerTest {
    @Mock
    private AirlineService airlineService;

    @InjectMocks
    private AirlineController airlineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNewAirline_Success() {
        // Mock airlineService.addNewAirline() method to return an airline
        AirlineRequest airlineRequest = new AirlineRequest();
        Airline airline = new Airline();
        when(airlineService.addNewAirline(any(AirlineRequest.class))).thenReturn(airline);

        // Call the controller method
        ResponseEntity<MessageModel> response = airlineController.addNewAirline(airlineRequest);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success add new airline", response.getBody().getMessage());
        assertEquals(airline, response.getBody().getData());
        verify(airlineService, times(1)).addNewAirline(airlineRequest);
    }

    @Test
    void testAddNewAirline_Failure() {
        // Mock airlineService.addNewAirline() method to return null
        AirlineRequest airlineRequest = new AirlineRequest();
        when(airlineService.addNewAirline(any(AirlineRequest.class))).thenReturn(null);

        // Call the controller method
        ResponseEntity<MessageModel> response = airlineController.addNewAirline(airlineRequest);

        // Verify the response
        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertEquals("failed to add airline", response.getBody().getMessage());
        verify(airlineService, times(1)).addNewAirline(airlineRequest);
    }

    @Test
    void testSearchByAirlineCode_Success() {
        // Mock airlineService.searchByAirlineCode() method to return an airline
        String airlineCode = "CGK";
        Airline airline = new Airline();
        when(airlineService.searchByAirlineCode(airlineCode)).thenReturn(airline);

        // Call the controller method
        ResponseEntity<MessageModel> response = airlineController.searchByAirlineCode(airlineCode);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success get airline by code CGK", response.getBody().getMessage());
        assertEquals(airline, response.getBody().getData());
        verify(airlineService, times(1)).searchByAirlineCode(airlineCode);
    }

    @Test
    void testSearchByAirlineCode_Failure() {
        // Mock airlineService.searchByAirlineCode() method to return null
        String airlineCode = "CGK";
        when(airlineService.searchByAirlineCode(airlineCode)).thenReturn(null);

        // Call the controller method
        ResponseEntity<MessageModel> response = airlineController.searchByAirlineCode(airlineCode);

        // Verify the response
        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertEquals("failed get airline by code CGK", response.getBody().getMessage());
        verify(airlineService, times(1)).searchByAirlineCode(airlineCode);
    }

    @Test
    void testGetAllAirline_Success() {
        // Mock airlineService.getAllAirline() method to return a list of airlines
        List<Airline> airlines = Collections.singletonList(new Airline());
        when(airlineService.getAllAirline()).thenReturn(airlines);

        // Call the controller method
        ResponseEntity<MessageModel> response = airlineController.getAllAirline();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success get all airline", response.getBody().getMessage());
        assertEquals(airlines.size(), ((List<AirlineResponse>) response.getBody().getData()).size());
        verify(airlineService, times(1)).getAllAirline();
    }

    @Test
    void testGetAllAirline_Failure() {
        // Mock airlineService.getAllAirline() method to return an empty list
        when(airlineService.getAllAirline()).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<MessageModel> response = airlineController.getAllAirline();

        // Verify the response
        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertEquals("failed to get all airline", response.getBody().getMessage());
        verify(airlineService, times(1)).getAllAirline();
    }
}
