package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.dto.TimeRequest;
import com.binar.pemesanantiketpesawat.service.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Time;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class TimeControllerTest {

    @Mock
    private TimeService timeService;

    @InjectMocks
    private TimeController timeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNewTime_Success() {
        // Arrange
        TimeRequest timeRequest = new TimeRequest(1, new Time(0), new Time(0));
        com.binar.pemesanantiketpesawat.model.Time timeResponse = new com.binar.pemesanantiketpesawat.model.Time();
        when(timeService.addNewTime(timeRequest)).thenReturn(timeResponse);

        // Act
        ResponseEntity<MessageModel> response = timeController.addNewTime(timeRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success add new time", response.getBody().getMessage());
        assertEquals(timeResponse, response.getBody().getData());
    }

    @Test
    void testAddNewTime_Failure() {
        // Arrange
        TimeRequest timeRequest = new TimeRequest(1, new Time(0), new Time(0));
        when(timeService.addNewTime(timeRequest)).thenReturn(null);

        // Act
        ResponseEntity<MessageModel> response = timeController.addNewTime(timeRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("failed add new time", response.getBody().getMessage());
        assertNull(response.getBody().getData());
    }

    @Test
    void testGetAllTime_NoData() {
        // Arrange
        MessageModel<List<com.binar.pemesanantiketpesawat.model.Time>> expectedMessageModel = new MessageModel<>();
        expectedMessageModel.setStatus(HttpStatus.NO_CONTENT.value());
        expectedMessageModel.setMessage("no time available");

        when(timeService.getAllTime()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<MessageModel> response = timeController.getAllTime();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessageModel, response.getBody());
    }

    @Test
    void testGetAllTime_WithData() {
        // Arrange
        List<com.binar.pemesanantiketpesawat.model.Time> timeList = Collections.singletonList(new com.binar.pemesanantiketpesawat.model.Time());
        MessageModel<List<com.binar.pemesanantiketpesawat.model.Time>> expectedMessageModel = new MessageModel<>();
        expectedMessageModel.setStatus(HttpStatus.OK.value());
        expectedMessageModel.setMessage("success get all time");
        expectedMessageModel.setData(timeList);

        when(timeService.getAllTime()).thenReturn(timeList);

        // Act
        ResponseEntity<MessageModel> response = timeController.getAllTime();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMessageModel, response.getBody());
    }
}
