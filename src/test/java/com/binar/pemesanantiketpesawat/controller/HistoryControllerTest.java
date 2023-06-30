package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.controller.HistoryController;
import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Disabled
public class HistoryControllerTest {
//
//    @Mock
//    private BookingService bookingService;
//
//    private HistoryController historyController;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        historyController = new HistoryController();
//        historyController.setBookingService(bookingService);
//    }
//
//    @Test
//    public void testFindByUUID_Success() {
//        UUID uuidRequest = UUID.randomUUID();
//        List<Booking> bookings = new ArrayList<>();
//        bookings.add(new Booking());
//        when(bookingService.findByUuidUser(uuidRequest)).thenReturn(bookings);
//
//        List<Booking> result = historyController.findByUUID(uuidRequest);
//
//        assertEquals(bookings, result);
//        verify(bookingService, times(1)).findByUuidUser(uuidRequest);
//    }
//
//    @Test
//    public void testFindByUUID_NoBookings() {
//        UUID uuidRequest = UUID.randomUUID();
//        when(bookingService.findByUuidUser(uuidRequest)).thenReturn(new ArrayList<>());
//
//        List<Booking> result = historyController.findByUUID(uuidRequest);
//
//        assertEquals(0, result.size());
//        verify(bookingService, times(1)).findByUuidUser(uuidRequest);
//    }
}
