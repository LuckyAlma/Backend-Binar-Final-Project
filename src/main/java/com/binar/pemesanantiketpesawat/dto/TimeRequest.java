package com.binar.pemesanantiketpesawat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeRequest {
    private Integer departureDateFk;
    private java.sql.Time departureTime;
    private java.sql.Time arrivalTime;
}


/*

    LIAT DATA NYA DI CHROME TERNYATA YANG DI CHROME CUMA DURATION DI SURUH GANTI KE MINUTES

 */