package com.binar.pemesanantiketpesawat.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceReport {
    String title;
    String fullName;
    String ticketType;
    String ticketNumber;
    String flightNumber;
    String facility;
}
