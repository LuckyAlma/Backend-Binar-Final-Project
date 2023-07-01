package com.binar.pemesanantiketpesawat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;
    @Column(name = "continent_category", nullable = false)
    private String continentCategory;
    @Column(name = "favorite_flight", nullable = false)
    private Boolean favoriteFlight;
    @Column(name = "departure_date", nullable = false)
    private Date departureDate;
    @Column(name = "departure_city", nullable = false)
    private String departureCity;
    @Column(name = "departure_airport", nullable = false)
    private String departureAirport;
    @Column(name = "arrival_city", nullable = false)
    private String arrivalCity;
    @Column(name = "arrival_airport", nullable = false)
    private String arrivalAirport;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "departure_date_fk", referencedColumnName = "schedule_id", insertable = true, updatable = true)
    private List<Time> schedulesList = new ArrayList<>(); // Inisialisasi dengan ArrayList kosong

    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    @JsonIgnore
    @Column(name = "modified_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    public Schedule(Integer scheduleId, Boolean favoriteFlight, Date departureDate, String departureCity, String arrivalCity, List<Time> schedulesList) {
        this.scheduleId = scheduleId;
        this.favoriteFlight = favoriteFlight;
        this.departureDate = departureDate;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.schedulesList = schedulesList;
    }

    public Schedule(Integer scheduleId, String continentCategory, Boolean favoriteFlight, Date departureDate, String departureCity, String departureAirport, String arrivalCity, String arrivalAirport, List<Time> schedulesList) {
        this.scheduleId = scheduleId;
        this.continentCategory = continentCategory;
        this.favoriteFlight = favoriteFlight;
        this.departureDate = departureDate;
        this.departureCity = departureCity;
        this.departureAirport = departureAirport;
        this.arrivalCity = arrivalCity;
        this.arrivalAirport = arrivalAirport;
        this.schedulesList = schedulesList;
    }

    public Schedule(String continentCategory, Boolean favoriteFlight, Date departureDate, String departureCity, String departureAirport, String arrivalCity, String arrivalAirport) {
        this.continentCategory = continentCategory;
        this.favoriteFlight = favoriteFlight;
        this.departureDate = departureDate;
        this.departureCity = departureCity;
        this.departureAirport = departureAirport;
        this.arrivalCity = arrivalCity;
        this.arrivalAirport = arrivalAirport;
    }
}
