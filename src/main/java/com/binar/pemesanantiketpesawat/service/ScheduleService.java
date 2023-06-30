package com.binar.pemesanantiketpesawat.service;

import com.binar.pemesanantiketpesawat.dto.DetailFlightList;
import com.binar.pemesanantiketpesawat.dto.FavoriteFlightModel;
import com.binar.pemesanantiketpesawat.dto.ScheduleRequest;
import com.binar.pemesanantiketpesawat.model.Schedule;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public interface ScheduleService {
    List<Schedule> getAllSchedules();
    List<Schedule> searchAirplaneTicketOnlyPlane(Date date, String departure, String arrival, String seat);
    List<Schedule> searchAirplaneTicketSchedule(Date date, String departure, String arrival, String seat);
    List<DetailFlightList> filterDataSchedule(Date date, String departure, String arrival, String seat);
    List<DetailFlightList> filterDataPriceDesc(Date departureDate, String departureAirport, String arrivalAirport, String seatClass);
    List<DetailFlightList> filterDataPriceAsc(Date departureDate, String departureAirport, String arrivalAirport, String seatClass);
    List<FavoriteFlightModel> findByFavoriteDestination();
    Schedule addSchedule(ScheduleRequest scheduleRequest);
    String calculateFlightDuration(String departureTime, String arrivalTime);
    void deleteAllAirplaneTicketSchedule();
    Schedule updateSchedule(Schedule scheduleRequest);
}
