package com.binar.pemesanantiketpesawat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceModelRequest {
    String bookingCode;
    String flightDate;//
    String airlineName;//
    String airlineCode;//
    String flightClass;//
    String departureTime;//
    String arrivalTime;//
    String departureAirport;//
    String arrivalAirport;//
    String departureCity; //
    String arrivalCity;//
    String departureGate; //
    String arrivalGate;//
    String longFlight;//
}
//
//{
//        "status": 200,
//        "message": "managed to get the data",
//        "data": {
//        "bookingId": 44,
//        "bookingCode": "A1342S",
//        "departureAirport": "Semarang",
//        "departureDate": "2023-06-24",
//        "departureTime": "14:34:19",
//        "departureGate": "A1",
//        "arrivalAirport": "Banjarmasin",
//        "arrivalDate1": "2023-06-24",
//        "arrivalTime": "19:00:20",
//        "arrivalGate": "B2",
//        "longFlight1": "4 Hours 26 Minutes",
//        "flightClass": "Economy",
//        "airlineCode": "GA-324",
//        "customers": {
//        "customerId": 41,
//        "fullName": "Lucky Alma Aficionado Rigel",
//        "familyName": "Rahayu Puji Hartanti",
//        "phoneNumber": "087719857757",
//        "email": "luckyrigel9802@gmail.com"
//        },
//        "passengers": [
//        {
//        "passengerId": 35,
//        "title": "Mr.",
//        "fullName": "Lucky Alma Aficionado Rigel",
//        "familyName": "Rahayu Puji Hartanti",
//        "dob": "2002-08-09",
//        "nationality": "Indonesia",
//        "identityNumber": 3826129078340223,
//        "identityIssuingCountry": "Indonesia",
//        "expiredAt": "2027-06-24"
//        }
//        ]
//        }
//        }