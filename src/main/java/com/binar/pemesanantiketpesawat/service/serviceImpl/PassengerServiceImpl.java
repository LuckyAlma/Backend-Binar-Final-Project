package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.exception.PassengerException;
import com.binar.pemesanantiketpesawat.model.Passenger;
import com.binar.pemesanantiketpesawat.repository.PassengerRepository;
import com.binar.pemesanantiketpesawat.request.PassengerRequest;
import com.binar.pemesanantiketpesawat.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Override
    public Passenger saveDataPenumpang(PassengerRequest the_passenger) {

        Passenger penumpang = new Passenger(
                the_passenger.getTitle(),
                the_passenger.getFullName(),
                the_passenger.getFamilyName(),
                the_passenger.getDob(),
                the_passenger.getNationality(),
                the_passenger.getIdentityNumber(),
                the_passenger.getIdentityIssuingCountry(),
                the_passenger.getExpiredAt()
        );

        penumpang.setPassengerId(0);

        return passengerRepository.save(penumpang);
    }


    @Override
    public List<Passenger> getAllPenumpang() {
        List<Passenger> passengerList = passengerRepository.findAll();

        // melakukan pengecekan apakah ada data penumpang
        if (passengerList.isEmpty()) {
            throw new PassengerException("tidak ada penumpang yang terdaftar!");
        }

        return passengerList;
    }


    @Override
    public Passenger findByIdPenumpang(Integer passenger_id) {
        return passengerRepository.findById(passenger_id).orElseThrow(() -> {
            throw new PassengerException("penumpang dengan id " + passenger_id + " tidak ditemukan");
        });
    }

    @Override
    public Passenger updateEntityPenumpang(Passenger the_passenger) {

        Passenger tempPenumpang = passengerRepository.findById(the_passenger.getPassengerId()).orElseThrow(() -> {
            throw new PassengerException("penumpang dengan id " + the_passenger.getPassengerId() + " tidak ditemukan");
        });

        tempPenumpang.setTitle(the_passenger.getTitle());
        tempPenumpang.setFullName(the_passenger.getFullName());
        tempPenumpang.setFamilyName(the_passenger.getFamilyName());
        tempPenumpang.setDob(the_passenger.getDob());
        tempPenumpang.setNationality(the_passenger.getNationality());
        tempPenumpang.setIdentityNumber(the_passenger.getIdentityNumber());
        tempPenumpang.setIdentityIssuingCountry(the_passenger.getIdentityIssuingCountry());
        tempPenumpang.setExpiredAt(the_passenger.getExpiredAt());

        passengerRepository.save(tempPenumpang);

        return tempPenumpang;
    }

    @Override
    public String deletePenumpang(Integer passenger_id) {
        passengerRepository.findById(passenger_id).orElseThrow(() -> {
            throw new PassengerException("penumpang dengan id " + passenger_id + " tidak ditemukan");
        });

        passengerRepository.deleteById(passenger_id);

        return "penumpang dengan id " + passenger_id + " berhasil dihapus";
    }

}
