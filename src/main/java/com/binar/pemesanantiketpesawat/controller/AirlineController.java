package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.AirlineRequest;
import com.binar.pemesanantiketpesawat.dto.AirlineResponse;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.service.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/airline")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @PostMapping("/add-airline")
    public ResponseEntity<MessageModel> addNewAirline(@RequestBody AirlineRequest airlineRequest) {


        MessageModel messageModel = new MessageModel();
        Airline airlineResponse = airlineService.addNewAirline(airlineRequest);
        if (airlineResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            messageModel.setMessage("failed to add new airline");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("success add new airline");
            messageModel.setData(airlineResponse);
            return ResponseEntity.ok().body(messageModel);
        }

    }

    @GetMapping("/airlineCode")
    public ResponseEntity<MessageModel> searchByAirlineCode(@RequestParam String codeRequest) {
        MessageModel messageModel = new MessageModel();
        Airline airlineResponse = airlineService.searchByAirlineCode(codeRequest);
        if (airlineResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("failed get airline by code " + codeRequest);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("success get airline by code " + codeRequest);
            messageModel.setData(airlineResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/all-airline")
    public ResponseEntity<MessageModel> getAllAirline() {
        MessageModel messageModel = new MessageModel();
        List<AirlineResponse> airlineResponse = airlineService.getAllAirline().stream()
                .map(airline -> new AirlineResponse(
                        airline.getAirlineId(),
                        airline.getAirlineName(),
                        airline.getAirlineCode()
                ))
                .collect(Collectors.toList());

        if (airlineResponse.isEmpty()) {
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            messageModel.setMessage("failed to get all airline");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("success get all airline");
            messageModel.setData(airlineResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

}
