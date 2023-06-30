package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private BookingService bookingRequest;

    @GetMapping
    private List<Booking> findByUUID(@RequestParam UUID uuidRequest) {
        return bookingRequest.findByUuidUser(uuidRequest);
    }

}
