package com.binar.pemesanantiketpesawat.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleRequest {
    private String continentCategory;
    private Boolean favoriteFlight;
    private Date departureDate;
    private String departureCity;
    private String arrivalCity;
}
