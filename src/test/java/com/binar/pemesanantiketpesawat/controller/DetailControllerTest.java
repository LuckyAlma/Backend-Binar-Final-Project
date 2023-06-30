package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.dto.DetailFlight;
import com.binar.pemesanantiketpesawat.service.DetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DetailControllerTest {

    @Mock
    private DetailService detailService;

    @InjectMocks
    private DetailController detailController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFilterDataFlight() {
        // Arrange
        String airlineCode = "GA-455";
        String flightClass = "Economy";
        Integer adultPassenger = 2;
        Integer childrenPassenger = 1;
        Integer babyPassenger = 0;

        DetailFlight expectedDetailFlight = new DetailFlight();
        // Set expected values for the DetailFlight object

        when(detailService.getDetailPenerbangan(airlineCode, flightClass, adultPassenger, childrenPassenger, babyPassenger))
                .thenReturn(expectedDetailFlight);

        // Act
        DetailFlight result = detailController.filterDataFlight(airlineCode, flightClass, adultPassenger, childrenPassenger, babyPassenger);

        // Assert
        assertEquals(expectedDetailFlight, result);
        verify(detailService, times(1)).getDetailPenerbangan(airlineCode, flightClass, adultPassenger, childrenPassenger, babyPassenger);
    }
}
