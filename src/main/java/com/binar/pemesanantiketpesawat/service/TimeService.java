package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.TimeRequest;
import com.binar.pemesanantiketpesawat.model.Time;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TimeService {

    Time addNewTime(TimeRequest timeRequest);

    List<Time> getAllTime();

}
