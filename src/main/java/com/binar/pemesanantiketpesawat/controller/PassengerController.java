package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.model.Passenger;
import com.binar.pemesanantiketpesawat.request.PassengerRequest;
import com.binar.pemesanantiketpesawat.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/getAll")
    List<Passenger> getAllPenumpang() {
        return passengerService.getAllPenumpang();
    }

    @PostMapping("/Save")
    Passenger saveDataPenumpang(@RequestBody PassengerRequest the_passenger){
        return passengerService.saveDataPenumpang(the_passenger);
    }

    @GetMapping("/findByIdPenumpang/{passenger_id}")
    Passenger findByIdPenumpang(@RequestParam Integer passenger_id) {
        return passengerService.findByIdPenumpang(passenger_id);
    }

    @PutMapping("/Update")
    Passenger updateEntityPenumpang(@RequestBody Passenger thePassenger) {
        return passengerService.updateEntityPenumpang(thePassenger);
    }


    @DeleteMapping("/delete/{passenger_id}")
    String deletePenumpang(@RequestParam Integer passenger_id) {
        return passengerService.deletePenumpang(passenger_id);
    }
}