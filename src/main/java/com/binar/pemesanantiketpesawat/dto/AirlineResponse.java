package com.binar.pemesanantiketpesawat.dto;

import lombok.Data;

@Data
public class AirlineResponse {
    private Integer airlineId;
    private String airlineName;
    private String airlineCode;

    public AirlineResponse(Integer airlineId, String airlineName, String airlineCode) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
    }
}
