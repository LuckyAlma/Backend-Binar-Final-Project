package com.binar.pemesanantiketpesawat.repository;

import com.binar.pemesanantiketpesawat.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Integer> {
    Time findByScheduleId(Integer scheduleId);
}
