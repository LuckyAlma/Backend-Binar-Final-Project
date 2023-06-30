package com.binar.pemesanantiketpesawat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FavoriteFlightModel {

    String category;
    String rute;
    String airline;
    String date;
    String price;
    String image;

}
