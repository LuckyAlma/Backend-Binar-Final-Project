package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.DetailFlight;
import org.springframework.stereotype.Component;

@Component
public interface DetailService {
    DetailFlight getDetailPenerbangan(
            String codeRequest, String classResponse,
            Integer adultsPassenggers, Integer childrensPassenggers,
            Integer babyPassenggers);

    DetailFlight getDetailPenerbanganByCodeRequestAndClassResponse(
            String codeRequest, String classResponse
    );
}
