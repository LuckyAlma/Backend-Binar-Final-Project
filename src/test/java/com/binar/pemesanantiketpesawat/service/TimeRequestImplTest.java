package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.TimeRequest;
import com.binar.pemesanantiketpesawat.model.Schedule;
import com.binar.pemesanantiketpesawat.model.Time;
import com.binar.pemesanantiketpesawat.repository.ScheduleRepository;
import com.binar.pemesanantiketpesawat.repository.TimeRepository;
import com.binar.pemesanantiketpesawat.service.serviceImpl.TimeRequestImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TimeRequestImplTest {

    @Mock
    private TimeRepository timeRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private TimeRequestImpl timeRequestImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewTime_ValidSchedule_ReturnsTimeObject() {
        // Arrange
        TimeRequest timeRequest = new TimeRequest(1, java.sql.Time.valueOf("10:00:00"), java.sql.Time.valueOf("12:00:00"));
        timeRequest.setDepartureDateFk(1);
        timeRequest.setDepartureTime(java.sql.Time.valueOf(LocalTime.parse("10:00:00")));
        timeRequest.setArrivalTime(java.sql.Time.valueOf(LocalTime.parse("12:00:00")));

        Schedule schedule = new Schedule();
        schedule.setScheduleId(1);

        when(scheduleRepository.findById(timeRequest.getDepartureDateFk())).thenReturn(Optional.of(schedule));
        when(timeRepository.save(any(Time.class))).thenReturn(new Time());

        // Act
        Time result = timeRequestImpl.addNewTime(timeRequest);

        // Assert
        Assertions.assertNotNull(result);
        verify(scheduleRepository, times(1)).findById(timeRequest.getDepartureDateFk());
        verify(timeRepository, times(1)).save(any(Time.class));
    }

    @Test
    public void testAddNewTime_InvalidSchedule_ReturnsNull() {
        // Arrange
        TimeRequest timeRequest = new TimeRequest(1, java.sql.Time.valueOf("10:00:00"), java.sql.Time.valueOf("12:00:00"));
        timeRequest.setDepartureDateFk(1);
        timeRequest.setDepartureTime(java.sql.Time.valueOf(LocalTime.parse("10:00:00")));
        timeRequest.setArrivalTime(java.sql.Time.valueOf(LocalTime.parse("12:00:00")));

        when(scheduleRepository.findById(timeRequest.getDepartureDateFk())).thenReturn(Optional.empty());

        // Act
        Time result = timeRequestImpl.addNewTime(timeRequest);

        // Assert
        Assertions.assertNull(result);
        verify(scheduleRepository, times(1)).findById(timeRequest.getDepartureDateFk());
        verify(timeRepository, never()).save(any(Time.class));
    }

    @Test
    public void testGetAllTime_ReturnsAllTimes() {
        // Arrange
        List<Time> expectedTimes = new ArrayList<>();
        expectedTimes.add(new Time());
        expectedTimes.add(new Time());

        when(timeRepository.findAll()).thenReturn(expectedTimes);

        // Act
        List<Time> result = timeRequestImpl.getAllTime();

        // Assert
        Assertions.assertEquals(expectedTimes, result);
        verify(timeRepository, times(1)).findAll();
    }
}
