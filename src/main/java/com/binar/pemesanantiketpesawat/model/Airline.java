package com.binar.pemesanantiketpesawat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airline")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id")
    private Integer airlineId;
    @Column(name = "airline_time_fk")
    private Integer airlineTimeFk;
    @Column(name = "airline_name")
    private String airlineName;
    @Column(name = "airline_code")
    private String airlineCode;
    @Column(name = "departure_gate")
    private String departureGate;
    @Column(name = "arrival_gate")
    private String arrivalGate;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "airline_code_fk", referencedColumnName = "airline_id", updatable = true, insertable = true)
    List<Seat> flightClass;
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "modified_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    public Airline(Integer airlineId, Integer airlineTimeFk, String airlineName, String airlineCode, List<Seat> flightClass) {
        this.airlineId = airlineId;
        this.airlineTimeFk = airlineTimeFk;
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
        this.flightClass = flightClass;
    }

    public Airline(Integer airlineTimeFk, String airlineName, String airlineCode, String departureGate, String arrivalGate, List<Seat> flightClass) {
        this.airlineTimeFk = airlineTimeFk;
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
        this.departureGate = departureGate;
        this.arrivalGate = arrivalGate;
        this.flightClass = flightClass;
    }
}
