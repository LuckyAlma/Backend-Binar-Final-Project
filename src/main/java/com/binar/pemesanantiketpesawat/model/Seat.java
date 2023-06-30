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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;
    @Column(name = "airline_code_fk")
    private Integer airlineCodeFk;
    @Column(name = "flight_class")
    private String flightClass;
    @Column(name = "airline_price")
    private String airlinePrice;
    @Column(name = "airline_baggage")
    private Integer airlineBaggage;
    @Column(name = "airline_cabin_baggage")
    private Integer airlineCabinBaggage;
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    @JsonIgnore
    @Column(name = "modified_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    public Seat(Integer seatId, Integer airlineCodeFk, String flightClass, String airlinePrice, Integer airlineBaggage, Integer airlineCabinBaggage) {
        this.seatId = seatId;
        this.airlineCodeFk = airlineCodeFk;
        this.flightClass = flightClass;
        this.airlinePrice = airlinePrice;
        this.airlineBaggage = airlineBaggage;
        this.airlineCabinBaggage = airlineCabinBaggage;
    }
}
