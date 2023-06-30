package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.AirportRequest;
import com.binar.pemesanantiketpesawat.model.Airport;
import com.binar.pemesanantiketpesawat.repository.AirportRepository;
import com.binar.pemesanantiketpesawat.repository.ScheduleRepository;
import com.binar.pemesanantiketpesawat.service.serviceImpl.AirportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AirportServiceImplTest {

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private AirportService airportService = new AirportServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewAirport_WhenAirportIATANotExists_ShouldReturnAirport() {
        // Arrange
        AirportRequest airportRequest = new AirportRequest("Jakarta", "DKI Jakarta", "CGK", "Soekarno-Hatta International Airport");
        when(airportRepository.existsByAirportIATA(airportRequest.getAirportIATA())).thenReturn(false);
        when(airportRepository.save(any(Airport.class))).thenReturn(new Airport(1, "Jakarta", "DKI Jakarta", "CGK", "Soekarno-Hatta International Airport"));

        // Act
        Airport result = airportService.addNewAirport(airportRequest);

        // Assert
        assertNotNull(result);
        assertEquals("Jakarta", result.getAirportLocation());
        assertEquals("DKI Jakarta", result.getAirportProvince());
        assertEquals("CGK", result.getAirportIATA());
        assertEquals("Soekarno-Hatta International Airport", result.getAirportName());
        verify(airportRepository, times(1)).existsByAirportIATA("CGK");
        verify(airportRepository, times(1)).save(any(Airport.class));
    }

    @Test
    public void testAddNewAirport_WhenAirportIATAExists_ShouldReturnNull() {
        // Arrange
        AirportRequest airportRequest = new AirportRequest("Jakarta", "DKI Jakarta", "CGK", "Soekarno-Hatta International Airport");
        when(airportRepository.existsByAirportIATA(airportRequest.getAirportIATA())).thenReturn(true);

        // Act
        Airport result = airportService.addNewAirport(airportRequest);

        // Assert
        assertNull(result);
        verify(airportRepository, times(1)).existsByAirportIATA("CGK");
        verify(airportRepository, never()).save(any(Airport.class));
    }

    @Test
    public void testGetDepartureAirport_ShouldReturnListOfDepartureAirports() {
        // Arrange
        List<String> expectedDepartureAirports = Arrays.asList("Jakarta", "Denpasar");
        when(scheduleRepository.findAllDepartureCity()).thenReturn(expectedDepartureAirports);

        // Act
        List<String> result = airportService.getDepartureAirport();

        // Assert
        assertNotNull(result);
        assertEquals(expectedDepartureAirports, result);
        verify(scheduleRepository, times(1)).findAllDepartureCity();
    }

    @Test
    public void testGetArrivalAirportFromDeparture_ShouldReturnListOfArrivalAirports() {
        // Arrange
        String departureCity = "Jakarta";
        List<String> expectedArrivalAirports = Arrays.asList("Denpasar", "Surabaya");
        when(scheduleRepository.findArrivalCityFromDepartureCity(departureCity)).thenReturn(expectedArrivalAirports);

        // Act
        List<String> result = airportService.getArrivalAirportFromDeparture(departureCity);

        // Assert
        assertNotNull(result);
        assertEquals(expectedArrivalAirports, result);
        verify(scheduleRepository, times(1)).findArrivalCityFromDepartureCity(departureCity);
    }

    @Test
    public void testGetAllAirport_WhenAirportResponseNotEmpty_ShouldReturnListOfAirports() {
        // Arrange
        List<String> expectedAirports = Arrays.asList("Jakarta", "Denpasar", "Surabaya");
        when(airportRepository.showAirportLocationOnly()).thenReturn(expectedAirports);

        // Act
        List<String> result = airportService.getAllAirport();

        // Assert
        assertNotNull(result);
        assertEquals(expectedAirports, result);
        verify(airportRepository, times(1)).showAirportLocationOnly();
    }

    @Test
    public void testGetAllAirport_WhenAirportResponseEmpty_ShouldReturnNull() {
        // Arrange
        when(airportRepository.showAirportLocationOnly()).thenReturn(Collections.emptyList());

        // Act
        List<String> result = airportService.getAllAirport();

        // Assert
        assertNull(result);
        verify(airportRepository, times(1)).showAirportLocationOnly();
    }
}
