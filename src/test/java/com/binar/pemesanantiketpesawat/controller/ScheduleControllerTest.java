package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.DetailFlightList;
import com.binar.pemesanantiketpesawat.dto.FavoriteFlightModel;
import com.binar.pemesanantiketpesawat.dto.MessageModel;
import com.binar.pemesanantiketpesawat.dto.ScheduleRequest;
import com.binar.pemesanantiketpesawat.model.Schedule;
import com.binar.pemesanantiketpesawat.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByFavoriteDestination_NoDataFound() {
        // Arrange
        MessageModel<List<FavoriteFlightModel>> expectedResponse = new MessageModel<>();
        expectedResponse.setStatus(HttpStatus.NO_CONTENT.value());
        expectedResponse.setMessage("no data found");

        when(scheduleService.findByFavoriteDestination()).thenReturn(null);

        // Act
        ResponseEntity<MessageModel> responseEntity = scheduleController.findByFavoriteDestination();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        verify(scheduleService, times(1)).findByFavoriteDestination();
        verifyNoMoreInteractions(scheduleService);
    }

    @Test
    void testFindByFavoriteDestination_Success() {
        // Arrange
        List<FavoriteFlightModel> favoriteFlightModels = new ArrayList<>();
        favoriteFlightModels.add(new FavoriteFlightModel("Asia", "Rute 1", "Garuda Indonesia", "2023-06-30", "100", "image1"));
        favoriteFlightModels.add(new FavoriteFlightModel("Eropa", "Rute 2", "British AirWays", "2023-07-01", "200", "image2"));

        MessageModel<List<FavoriteFlightModel>> expectedResponse = new MessageModel<>();
        expectedResponse.setStatus(HttpStatus.OK.value());
        expectedResponse.setMessage("managed to get the data");
        expectedResponse.setData(favoriteFlightModels);

        when(scheduleService.findByFavoriteDestination()).thenReturn(favoriteFlightModels);

        // Act
        ResponseEntity<MessageModel> responseEntity = scheduleController.findByFavoriteDestination();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        verify(scheduleService, times(1)).findByFavoriteDestination();
        verifyNoMoreInteractions(scheduleService);
    }

}
