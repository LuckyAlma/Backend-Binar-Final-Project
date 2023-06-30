package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.model.Passenger;
import com.binar.pemesanantiketpesawat.request.PassengerRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PassengerService {

    Passenger saveDataPenumpang(PassengerRequest the_passenger);

    List<Passenger> getAllPenumpang();

    Passenger findByIdPenumpang(Integer passenger_id);

    Passenger updateEntityPenumpang(Passenger the_passenger);

    String deletePenumpang(Integer passenger_id);
}
