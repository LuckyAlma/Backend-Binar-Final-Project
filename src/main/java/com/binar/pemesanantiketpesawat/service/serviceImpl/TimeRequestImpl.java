package com.binar.pemesanantiketpesawat.service.serviceImpl;

import com.binar.pemesanantiketpesawat.dto.TimeRequest;
import com.binar.pemesanantiketpesawat.model.Time;
import com.binar.pemesanantiketpesawat.repository.ScheduleRepository;
import com.binar.pemesanantiketpesawat.repository.TimeRepository;
import com.binar.pemesanantiketpesawat.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Service
public class TimeRequestImpl implements TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Time addNewTime(TimeRequest timeRequest) {

        boolean scheduleResponse = scheduleRepository.findById(timeRequest.getDepartureDateFk()).isPresent();

        if (!scheduleResponse) {
            return null;
        }
        LocalTime from = timeRequest.getDepartureTime().toLocalTime();
        LocalTime to = timeRequest.getArrivalTime().toLocalTime();

        Duration duration = Duration.between(from, to);

        String flightDuration = duration.toHours() + " Hours " + duration.toMinutesPart() + " Minutes";


        return timeRepository.save(new Time(
                0,
                timeRequest.getDepartureDateFk(),
                timeRequest.getDepartureTime(),
                timeRequest.getArrivalTime(),
                flightDuration,
                null
        ));
    }

    @Override
    public List<Time> getAllTime() {
        return timeRepository.findAll();
    }
}
