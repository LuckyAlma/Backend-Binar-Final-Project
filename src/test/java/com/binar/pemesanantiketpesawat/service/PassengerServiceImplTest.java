package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.exception.PassengerException;
import com.binar.pemesanantiketpesawat.model.Passenger;
import com.binar.pemesanantiketpesawat.repository.PassengerRepository;
import com.binar.pemesanantiketpesawat.request.PassengerRequest;
import com.binar.pemesanantiketpesawat.service.serviceImpl.PassengerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PassengerServiceImplTest {

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerServiceImpl passengerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDataPenumpang_Success() {
        PassengerRequest passengerRequest = createPassengerRequest();
        Passenger passenger = createPassenger();

        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);

        Passenger result = passengerService.saveDataPenumpang(passengerRequest);

        assertNotNull(result);
        assertEquals(passenger.getTitle(), result.getTitle());
        assertEquals(passenger.getFullName(), result.getFullName());
        assertEquals(passenger.getFamilyName(), result.getFamilyName());
        assertEquals(passenger.getDob(), result.getDob());
        assertEquals(passenger.getNationality(), result.getNationality());
        assertEquals(passenger.getIdentityNumber(), result.getIdentityNumber());
        assertEquals(passenger.getIdentityIssuingCountry(), result.getIdentityIssuingCountry());
        assertEquals(passenger.getExpiredAt(), result.getExpiredAt());

        verify(passengerRepository, times(1)).save(any(Passenger.class));
    }

    @Test
    public void testGetAllPenumpang_Success() {
        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(createPassenger());
        when(passengerRepository.findAll()).thenReturn(passengerList);

        List<Passenger> result = passengerService.getAllPenumpang();

        assertNotNull(result);
        assertEquals(passengerList.size(), result.size());

        verify(passengerRepository, times(1)).findAll();
        verifyNoMoreInteractions(passengerRepository); // Add this line to verify no additional invocations
    }

    @Test
    public void testGetAllPenumpang_EmptyList() {
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>());

        PassengerException exception = assertThrows(PassengerException.class, () -> {
            passengerService.getAllPenumpang();
        });

        assertEquals("tidak ada penumpang yang terdaftar!", exception.getMessage());

        verify(passengerRepository, times(1)).findAll();
    }

    @Test
    public void testFindByIdPenumpang_Success() {
        Integer passengerId = 1;
        Passenger passenger = createPassenger();
        when(passengerRepository.findById(passengerId)).thenReturn(Optional.of(passenger));

        Passenger result = passengerService.findByIdPenumpang(passengerId);

        assertNotNull(result);
        assertEquals(passenger.getTitle(), result.getTitle());
        assertEquals(passenger.getFullName(), result.getFullName());
        assertEquals(passenger.getFamilyName(), result.getFamilyName());
        assertEquals(passenger.getDob(), result.getDob());
        assertEquals(passenger.getNationality(), result.getNationality());
        assertEquals(passenger.getIdentityNumber(), result.getIdentityNumber());
        assertEquals(passenger.getIdentityIssuingCountry(), result.getIdentityIssuingCountry());
        assertEquals(passenger.getExpiredAt(), result.getExpiredAt());

        verify(passengerRepository, times(1)).findById(passengerId);
    }

    @Test
    public void testFindByIdPenumpang_NotFound() {
        Integer passengerId = 1;
        when(passengerRepository.findById(passengerId)).thenReturn(Optional.empty());

        PassengerException exception = assertThrows(PassengerException.class, () -> {
            passengerService.findByIdPenumpang(passengerId);
        });

        assertEquals("penumpang dengan id " + passengerId + " tidak ditemukan", exception.getMessage());

        verify(passengerRepository, times(1)).findById(passengerId);
    }

    @Test
    public void testUpdateEntityPenumpang_Success() {
        Passenger passenger = createPassenger();
        when(passengerRepository.findById(passenger.getPassengerId())).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);

        Passenger updatedPassenger = createUpdatedPassenger();
        Passenger result = passengerService.updateEntityPenumpang(updatedPassenger);

        assertNotNull(result);
        assertEquals(updatedPassenger.getTitle(), result.getTitle());
        assertEquals(updatedPassenger.getFullName(), result.getFullName());
        assertEquals(updatedPassenger.getFamilyName(), result.getFamilyName());
        assertEquals(updatedPassenger.getDob(), result.getDob());
        assertEquals(updatedPassenger.getNationality(), result.getNationality());
        assertEquals(updatedPassenger.getIdentityNumber(), result.getIdentityNumber());
        assertEquals(updatedPassenger.getIdentityIssuingCountry(), result.getIdentityIssuingCountry());
        assertEquals(updatedPassenger.getExpiredAt(), result.getExpiredAt());

        verify(passengerRepository, times(1)).findById(updatedPassenger.getPassengerId());
        verify(passengerRepository, times(1)).save(any(Passenger.class));
    }

    @Test
    public void testUpdateEntityPenumpang_NotFound() {
        Passenger passenger = createUpdatedPassenger();
        when(passengerRepository.findById(passenger.getPassengerId())).thenReturn(Optional.empty());

        PassengerException exception = assertThrows(PassengerException.class, () -> {
            passengerService.updateEntityPenumpang(passenger);
        });

        assertEquals("penumpang dengan id " + passenger.getPassengerId() + " tidak ditemukan", exception.getMessage());

        verify(passengerRepository, times(1)).findById(passenger.getPassengerId());
    }

    @Test
    public void testDeletePenumpang_Success() {
        Integer passengerId = 1;
        Passenger passenger = createPassenger();
        when(passengerRepository.findById(passengerId)).thenReturn(Optional.of(passenger));

        String result = passengerService.deletePenumpang(passengerId);

        assertEquals("penumpang dengan id " + passengerId + " berhasil dihapus", result);

        verify(passengerRepository, times(1)).findById(passengerId);
        verify(passengerRepository, times(1)).deleteById(passengerId);
    }

    @Test
    public void testDeletePenumpang_NotFound() {
        Integer passengerId = 1;
        when(passengerRepository.findById(passengerId)).thenReturn(Optional.empty());

        PassengerException exception = assertThrows(PassengerException.class, () -> {
            passengerService.deletePenumpang(passengerId);
        });

        assertEquals("penumpang dengan id " + passengerId + " tidak ditemukan", exception.getMessage());

        verify(passengerRepository, times(1)).findById(passengerId);
    }

    private PassengerRequest createPassengerRequest() {
        PassengerRequest passengerRequest = new PassengerRequest();
        passengerRequest.setTitle("Mr");
        passengerRequest.setFullName("John Doe");
        passengerRequest.setFamilyName("Doe");
        passengerRequest.setDob(new Date(System.currentTimeMillis()));
        passengerRequest.setNationality("Indonesia");
        passengerRequest.setIdentityNumber(1234567890L);
        passengerRequest.setIdentityIssuingCountry("Indonesia");
        passengerRequest.setExpiredAt(new Date(System.currentTimeMillis()));
        return passengerRequest;
    }

    private Passenger createPassenger() {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        passenger.setTitle("Mr");
        passenger.setFullName("John Doe");
        passenger.setFamilyName("Doe");
        passenger.setDob(new Date(System.currentTimeMillis()));
        passenger.setNationality("Indonesia");
        passenger.setIdentityNumber(1234567890L);
        passenger.setIdentityIssuingCountry("Indonesia");
        passenger.setExpiredAt(new Date(System.currentTimeMillis()));
        return passenger;
    }

    private Passenger createUpdatedPassenger() {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        passenger.setTitle("Ms");
        passenger.setFullName("Jane Smith");
        passenger.setFamilyName("Smith");
        passenger.setDob(new Date(System.currentTimeMillis()));
        passenger.setNationality("United States");
        passenger.setIdentityNumber(9876543210L);
        passenger.setIdentityIssuingCountry("United States");
        passenger.setExpiredAt(new Date(System.currentTimeMillis()));
        return passenger;
    }
}
