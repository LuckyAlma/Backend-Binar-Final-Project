package com.binar.pemesanantiketpesawat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "time")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Integer timeId;
    @JsonIgnore
    @Column(name = "departure_date_fk")
    private Integer departureDateFk;
    @Column(name = "departure_time", nullable = false)
    private java.sql.Time departureTime;
    @Column(name = "arrival_time", nullable = false)
    private java.sql.Time arrivalTime;
    @Column(name = "long_flight")
    private String longFlight;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "airline_time_fk", referencedColumnName = "time_id", insertable = true, updatable = true)
    private List<Airline> airlineList;
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    @JsonIgnore
    @Column(name = "modified_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    public Time(Integer timeId, Integer departureDateFk, java.sql.Time departureTime, java.sql.Time arrivalTime,String longFlight, List<Airline> airlineList) {
        this.timeId = timeId;
        this.departureDateFk = departureDateFk;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.longFlight = longFlight;
        this.airlineList = airlineList;
    }
}
