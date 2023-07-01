package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Date;
import java.util.List;

@EnableJpaRepositories
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByDepartureDateAndDepartureCityAndArrivalCity(Date date, String departure, String arrival);
    Schedule findByScheduleId(Integer idRequest);
    @Query(value = "SELECT DISTINCT s.arrivalCity FROM Schedule s WHERE s.departureCity = ?1")
    List<String> findArrivalCityFromDepartureCity(String departureCity);
    @Query(value = "SELECT DISTINCT s.departureCity FROM Schedule s")
    List<String> findAllDepartureCity();
    List<Schedule> findByFavoriteFlight(boolean b);
}
