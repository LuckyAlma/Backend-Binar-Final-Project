package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Booking findBookingByBookingCode(String bookingCodeRequest);

    List<Booking> findByUuidUser(UUID uuidRequest);
}
