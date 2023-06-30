package com.binar.pemesanantiketpesawat.controller;

import com.binar.pemesanantiketpesawat.model.Passenger;
import com.binar.pemesanantiketpesawat.request.PassengerRequest;
import com.binar.pemesanantiketpesawat.service.PassengerService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PassengerControllerTest {
    private MockMvc mockMvc;
    @Mock
    private PassengerService passengerService;
    @InjectMocks
    private PassengerController passengerController;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(passengerController).build();
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }

    @Test
    void testGetAllPassengers() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob1 = dateFormat.parse("1990-01-01");
        Date dob2 = dateFormat.parse("1992-02-02");
        Date expiredAt1 = dateFormat.parse("2023-01-01");
        Date expiredAt2 = dateFormat.parse("2024-02-02");

        Passenger passenger1 = new Passenger();
        passenger1.setTitle("Mr.");
        passenger1.setFullName("Aditya Prabowo");
        passenger1.setFamilyName("Ravenclaw");
        passenger1.setDob(new java.sql.Date(dob1.getTime()));
        passenger1.setNationality("USA");
        passenger1.setIdentityNumber(1234567890L);
        passenger1.setIdentityIssuingCountry("USA");
        passenger1.setExpiredAt(new java.sql.Date(expiredAt1.getTime()));

        Passenger passenger2 = new Passenger();
        passenger2.setTitle("Ms.");
        passenger2.setFullName("Dini Pitaloka");
        passenger2.setFamilyName("Ravenclaw");
        passenger2.setDob(new java.sql.Date(dob2.getTime()));
        passenger2.setNationality("UK");
        passenger2.setIdentityNumber(9876543210L);
        passenger2.setIdentityIssuingCountry("UK");
        passenger2.setExpiredAt(new java.sql.Date(expiredAt2.getTime()));
    }
}