package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.BookingRequest;
import com.binar.pemesanantiketpesawat.dto.DetailFlight;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.model.Customers;
import com.binar.pemesanantiketpesawat.model.Passenger;
import com.binar.pemesanantiketpesawat.repository.AirlineRepository;
import com.binar.pemesanantiketpesawat.repository.BookingRepository;
import com.binar.pemesanantiketpesawat.service.serviceImpl.BookingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private DetailService detailService;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveDataBooking() {
        // Membuat objek Customers
        Customers customers = new Customers();
        customers.setFullName("John Doe");
        customers.setEmail("john@example.com");
        customers.setPhoneNumber("1234567890");

// Membuat objek List<Passenger>
        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger1 = new Passenger();
        passenger1.setFullName("Passenger 1");
        passenger1.setDob(new Date(2000, 1, 1));
        passenger1.setTitle("Mr.");

        Passenger passenger2 = new Passenger();
        passenger2.setFullName("Passenger 2");
        passenger2.setDob(new Date(1990, 1, 1));
        passenger2.setTitle("Ms.");

        passengers.add(passenger1);
        passengers.add(passenger2);

        // Mocking the necessary dependencies
        BookingRequest bookingRequest = new BookingRequest(UUID.randomUUID(), "ABC123", "Economy", 1, 0, 0, customers, passengers );
        bookingRequest.setAirlineCode("ABC");
        bookingRequest.setFlightClass("Economy");
        bookingRequest.setAdult(1);
        bookingRequest.setChild(0);

        DetailFlight detailFlight = new DetailFlight();
        detailFlight.setDepartureAirport("Airport A");
        detailFlight.setDepartureDate(Date.valueOf("2021-01-01"));
        detailFlight.setDepartureTime(Time.valueOf("10:00:00"));
        detailFlight.setArrivalAirport("Airport B");
        detailFlight.setArrivalDate(Date.valueOf("2021-01-01"));
        detailFlight.setArrivalTime(Time.valueOf("13:00:00"));
        detailFlight.setLongFlight(String.valueOf(false));
        detailFlight.setAdultPrice(3_750_000);
        detailFlight.setChildPrice(1_000_000);
        detailFlight.setTotalPrice(detailFlight.getAdultPrice() + detailFlight.getChildPrice());

        Airline airline = new Airline();
        airline.setAirlineCode("ABC");
        airline.setAirlineName("Airline ABC");
        airline.setDepartureGate("Gate A");
        airline.setArrivalGate("Gate B");

        when(detailService.getDetailPenerbangan("ABC", "Economy", 1, 0, 0)).thenReturn(detailFlight);
        when(airlineRepository.findByAirlineCode("ABC")).thenReturn(airline);
        when(bookingRepository.save(any(Booking.class))).thenReturn(new Booking());

        // Call the method to be tested
        Booking result = bookingService.saveDataBooking(bookingRequest);

        // Verify the interactions and assertions
        verify(detailService, times(1)).getDetailPenerbangan("ABC", "Economy", 1, 0, 0);
        verify(airlineRepository, times(1)).findByAirlineCode("ABC");
        verify(bookingRepository, times(1)).save(any(Booking.class));

        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetAllPesanan() {
        // Mocking the necessary dependencies
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        bookings.add(new Booking());

        when(bookingRepository.findAll()).thenReturn(bookings);

        // Call the method to be tested
        List<Booking> result = bookingService.getAllPesanan();

        // Verify the interactions and assertions
        verify(bookingRepository, times(1)).findAll();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testFindByUuidUser() {
        // Mocking the necessary dependencies
        UUID uuid = UUID.randomUUID();
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        bookings.add(new Booking());

        when(bookingRepository.findByUuidUser(uuid)).thenReturn(bookings);

        // Call the method to be tested
        List<Booking> result = bookingService.findByUuidUser(uuid);

        // Verify the interactions and assertions
        verify(bookingRepository, times(1)).findByUuidUser(uuid);

        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testSearchBookingByCode() {
        // Mocking the necessary dependencies
        String bookingCode = "ABC123";
        Booking booking = new Booking();

        when(bookingRepository.findBookingByBookingCode(bookingCode)).thenReturn(booking);

        // Call the method to be tested
        Booking result = bookingService.searchBookingByCode(bookingCode);

        // Verify the interactions and assertions
        verify(bookingRepository, times(1)).findBookingByBookingCode(bookingCode);

        Assertions.assertNotNull(result);
    }
}