package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.FavoriteFlightModel;
import com.binar.pemesanantiketpesawat.dto.ScheduleRequest;
import com.binar.pemesanantiketpesawat.model.Airport;
import com.binar.pemesanantiketpesawat.model.Schedule;
import com.binar.pemesanantiketpesawat.repository.AirportRepository;
import com.binar.pemesanantiketpesawat.repository.ScheduleRepository;
import com.binar.pemesanantiketpesawat.service.serviceImpl.ScheduleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.eclipse.jdt.internal.compiler.problem.ProblemSeverities.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ScheduleServiceImplTest {
    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllSchedules() {
        // Mock data
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule());
        schedules.add(new Schedule());
        when(scheduleRepository.findAll()).thenReturn(schedules);

        // Call the method
        List<Schedule> result = scheduleService.getAllSchedules();

        // Verify the result
        assertEquals(2, result.size());
        verify(scheduleRepository, times(1)).findAll();
    }

    @Test
    public void testSearchAirplaneTicketSchedule() {
        // Mock data
        Date date = new Date(System.currentTimeMillis());
        String departure = "DepartureCity";
        String arrival = "ArrivalCity";
        String seat = "Economy";

        List<Schedule> schedules = new ArrayList<>();
        Schedule schedule1 = new Schedule();
        schedule1.setSchedulesList(new ArrayList<>()); // Atur schedulesList dengan ArrayList kosong
        Schedule schedule2 = new Schedule();
        schedule2.setSchedulesList(new ArrayList<>()); // Atur schedulesList dengan ArrayList kosong
        schedules.add(schedule1);
        schedules.add(schedule2);

        when(scheduleRepository.findByDepartureDateAndDepartureCityAndArrivalCity(
                any(Date.class), anyString(), anyString())).thenReturn(schedules);

        // Call the method
        List<Schedule> result = scheduleService.searchAirplaneTicketSchedule(date, departure, arrival, seat);

        // Verify the result
        assertEquals(2, result.size());
        verify(scheduleRepository, times(1))
                .findByDepartureDateAndDepartureCityAndArrivalCity(date, departure, arrival);

        // Check if schedulesList is not null
        result.forEach(schedule -> assertNotNull(schedule.getSchedulesList()));
    }

    @Test
    public void testAddSchedule() {
        // Mock data
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setDepartureCity("DepartureCity");
        scheduleRequest.setArrivalCity("ArrivalCity");

        Airport mockAirport = new Airport();
        when(airportRepository.findByAirportLocation(anyString())).thenReturn(mockAirport);

        Schedule savedSchedule = new Schedule();
        savedSchedule.setDepartureCity("DepartureCity");
        savedSchedule.setArrivalCity("ArrivalCity");
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(savedSchedule);

        // Call the method
        Schedule result = scheduleService.addSchedule(scheduleRequest);

        // Verify the result
        assertEquals("DepartureCity", result.getDepartureCity());
        assertEquals("ArrivalCity", result.getArrivalCity());
        verify(scheduleRepository, times(1)).save(any(Schedule.class));
    }

    @Test
    public void testDeleteAllAirplaneTicketSchedule() {
        // Call the method
        scheduleService.deleteAllAirplaneTicketSchedule();

        // Verify the method call
        verify(scheduleRepository, times(1)).deleteAll();
    }


    @Disabled
    @Test
    public void testUpdateSchedule() {
        // Mock data
        int timeId = 1;
        String departureCity = "DepartureCity";
        String arrivalCity = "ArrivalCity";

        Schedule scheduleRequest = new Schedule();
        scheduleRequest.setTimeId(timeId);
        scheduleRequest.setDepartureCity(departureCity);
        scheduleRequest.setArrivalCity(arrivalCity);

        Schedule existingSchedule = new Schedule();
        existingSchedule.setTimeId(timeId);
        existingSchedule.setDepartureCity("OldDepartureCity");
        existingSchedule.setArrivalCity("OldArrivalCity");

        when(scheduleRepository.findById(timeId)).thenReturn(java.util.Optional.ofNullable(existingSchedule));
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(existingSchedule);

        // Call the method
        Schedule result = scheduleService.updateSchedule(scheduleRequest);

        // Verify the result
        assertNotNull(result);
        assertEquals(timeId, result.getTimeId());
        assertEquals(departureCity, result.getDepartureCity());
        assertEquals(arrivalCity, result.getArrivalCity());
        verify(scheduleRepository, times(1)).findById(timeId);
        verify(scheduleRepository, times(1)).save(existingSchedule);
    }


    @Disabled
    @Test
    public void testFindByFavoriteDestination() {
        // Mock data
        List<Schedule> favoriteFlights = new ArrayList<>();
        favoriteFlights.add(new Schedule());
        favoriteFlights.add(new Schedule());

        when(scheduleRepository.findByFavoriteFlight(true)).thenReturn(favoriteFlights);

        // Call the method
        List<FavoriteFlightModel> result = new ArrayList<>();
        for (Schedule schedule : favoriteFlights) {
            FavoriteFlightModel favoriteFlightModel = new FavoriteFlightModel("Domestic", "Jakarta - Bali", "Garuda Indonesia", "2023-07-01", "5000000", "https://example.com/image.jpg");
            favoriteFlightModel.setCategory(schedule.getContinentCategory());
            favoriteFlightModel.setRute(schedule.getDepartureCity() + " - " + schedule.getArrivalCity());

            // Set properti lainnya dengan nilai yang sesuai
            // Misalnya:
            favoriteFlightModel.setAirline("Default Airline");
            favoriteFlightModel.setDate("2023-07-01");
            favoriteFlightModel.setPrice("5000000");
            favoriteFlightModel.setImage("https://example.com/image.jpg");

            // Add favoriteFlightModel to the result
            result.add(favoriteFlightModel);
        }
        // Verify the result
        assertEquals(2, result.size());
        verify(scheduleRepository, times(1)).findByFavoriteFlight(true);
    }
}
