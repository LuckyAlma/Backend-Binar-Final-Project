package com.binar.pemesanantiketpesawat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceModel {
    String title;
    String fullName;
    String ticketType;
    String ticketNumber;
    String flightNumber;
    String facility;
}
