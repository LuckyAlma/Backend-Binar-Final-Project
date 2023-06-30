package com.binar.pemesanantiketpesawat.controller;


import com.binar.pemesanantiketpesawat.dto.BookingRequest;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Booking;
import com.binar.pemesanantiketpesawat.service.BookingService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    Booking addBooking(@RequestBody BookingRequest bookingRequest) {
        return bookingService.saveDataBooking(bookingRequest);
    }

    @GetMapping("/search-booking-code")
    ResponseEntity<MessageModel> getBookingByCodeRequest(@RequestParam("bookingCodeRequest") String bookingCodeRequest) {
        Booking bookingResponse = bookingService.searchBookingByCode(bookingCodeRequest);
        MessageModel<Booking> messageModel = new MessageModel<>();

        if (bookingResponse == null) {
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
            messageModel.setMessage("no data found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("managed to get the data");
            messageModel.setData(bookingResponse);
            return ResponseEntity.ok(messageModel);
        }
    }


    @GetMapping("/getDataPemesan")
    public List<Booking> getDataPemesan() {
        return bookingService.getAllPesanan();
    }


}