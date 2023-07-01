package com.binar.pemesanantiketpesawat.service;

import static org.junit.jupiter.api.Assertions.*;

import com.binar.pemesanantiketpesawat.dto.DetailFlight;
import com.binar.pemesanantiketpesawat.dto.DetailFlightList;
import com.binar.pemesanantiketpesawat.model.Airline;
import com.binar.pemesanantiketpesawat.model.Schedule;
import com.binar.pemesanantiketpesawat.model.Seat;
import com.binar.pemesanantiketpesawat.model.Time;
import com.binar.pemesanantiketpesawat.repository.AirlineRepository;
import com.binar.pemesanantiketpesawat.repository.ScheduleRepository;
import com.binar.pemesanantiketpesawat.repository.SeatRepository;
import com.binar.pemesanantiketpesawat.repository.TimeRepository;
import com.binar.pemesanantiketpesawat.service.serviceImpl.DetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class DetailServiceImplTest {

    @Mock
    private TimeRepository timeRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private DetailServiceImpl detailService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDetailPenerbangan_PositiveTest() {
        // Mock data
        String codeRequest = "A001";
        String classResponse = "Economy";
        Integer adultsPassengers = 2;
        Integer childrenPassengers = 1;
        Integer babyPassengers = 0;

        Airline airlineResponse = new Airline();
        airlineResponse.setAirlineName("Airline A");
        airlineResponse.setAirlineCode("A001");
        airlineResponse.setAirlineTimeFk(1);

        Seat seatResponse = new Seat();
        seatResponse.setAirlineCodeFk(1);
        seatResponse.setFlightClass("Economy");
        seatResponse.setAirlineBaggage(20);
        seatResponse.setAirlineCabinBaggage(7);
        seatResponse.setAirlinePrice("1500000");

        Time timeResponse = new Time();
        timeResponse.setDepartureTime(java.sql.Time.valueOf("09:00:00"));
        timeResponse.setArrivalTime(java.sql.Time.valueOf("12:00:00"));
        timeResponse.setLongFlight("3 hours");
        timeResponse.setDepartureDateFk(1);

        Schedule scheduleResponse = new Schedule();
        scheduleResponse.setDepartureCity("City A");
        scheduleResponse.setArrivalCity("City B");
        scheduleResponse.setDepartureDate(java.sql.Date.valueOf(LocalDate.of(2023, Month.JUNE, 30)));
        scheduleResponse.setScheduleId(1);

        List<Seat> seatList = new ArrayList<>();
        seatList.add(seatResponse);

        // Mock repository methods
        when(airlineRepository.findByAirlineCode(codeRequest)).thenReturn(airlineResponse);
        when(seatRepository.findByAirlineCodeFkAndFlightClass(airlineResponse.getAirlineId(), classResponse)).thenReturn(seatList);
        when(timeRepository.findByTimeId(airlineResponse.getAirlineTimeFk())).thenReturn(timeResponse);
        when(scheduleRepository.findByScheduleId(timeResponse.getDepartureDateFk())).thenReturn(scheduleResponse);

        // Call the method
        DetailFlight result = detailService.getDetailPenerbangan(codeRequest, classResponse, adultsPassengers, childrenPassengers, babyPassengers);

        // Verify the result
        assertEquals("City A", result.getDepartureAirport());
        assertEquals("City B", result.getArrivalAirport());
        assertEquals(20, result.getCheckedBaggage());
        assertEquals(7, result.getCabinBaggage());
        assertEquals(4800000, result.getTotalPrice());

        // Verify that repository methods were called
        verify(airlineRepository, times(1)).findByAirlineCode(codeRequest);
        verify(seatRepository, times(1)).findByAirlineCodeFkAndFlightClass(airlineResponse.getAirlineId(), classResponse);
        verify(timeRepository, times(1)).findByTimeId(airlineResponse.getAirlineTimeFk());
        verify(scheduleRepository, times(1)).findByScheduleId(timeResponse.getDepartureDateFk());
    }

    @Test
    void getDetailPenerbanganByCodeRequestAndClassResponse_NegativeTest() {
        // Mock data
        String codeRequest = "A002";
        String classResponse = "Business";

        // Mock repository method
        when(airlineRepository.findByAirlineCode(codeRequest)).thenReturn(null);

        // Call the method
        assertThrows(NullPointerException.class, () -> detailService.getDetailPenerbanganByCodeRequestAndClassResponse(codeRequest, classResponse));

        // Verify that repository method was called
        verify(airlineRepository, times(1)).findByAirlineCode(codeRequest);
        verifyNoMoreInteractions(seatRepository, timeRepository, scheduleRepository);
    }
}
