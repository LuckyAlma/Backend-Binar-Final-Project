package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.AirportRequest;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Airport;
import com.binar.pemesanantiketpesawat.service.AirportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping("/get-airport")
    public ResponseEntity<MessageModel> getAllAirport() {
        MessageModel messageModel = new MessageModel();
        List<String> airportResponse = airportService.getAllAirport();
        if (airportResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to get airport");
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success to get airport");
            messageModel.setData(airportResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/getDepartureAirport")
    public ResponseEntity<MessageModel> getDepartureAirport() {
        MessageModel messageModel = new MessageModel();
        List<String> airportResponse = airportService.getDepartureAirport();
        if (airportResponse == null) {
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
            messageModel.setMessage("Failed to get airport");
            return ResponseEntity.noContent().build();
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success to get airport");
            messageModel.setData(airportResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/getArrivalAirport")
    public ResponseEntity<MessageModel> getDepartureAirport(@RequestParam String departureRequest) {
        MessageModel messageModel = new MessageModel();
        List<String> airportResponse = airportService.getArrivalAirportFromDeparture(departureRequest);
        if (airportResponse == null) {
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
            messageModel.setMessage("Failed to get airport");
            return ResponseEntity.noContent().build();
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success to get airport");
            messageModel.setData(airportResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @PostMapping("/add-airport")
    public ResponseEntity<MessageModel> addNewAirport(@RequestBody AirportRequest airportRequest) {
        log.info("========= ADD AIRPORT =========");
        MessageModel messageModel = new MessageModel();
        Airport airportResponse = airportService.addNewAirport(airportRequest);
        if (airportResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add airport");
            log.info("Status: Failed");
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Add new airport");
            messageModel.setData(airportResponse);
            log.info("Status: Success");
            log.info("Data: " + airportResponse.getAirportIATA());
            log.info("========= END AIRPORT =========");
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
