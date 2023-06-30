package com.binar.pemesanantiketpesawat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailFlight {

    private String departureAirport;
    private Time departureTime;
    private Date departureDate;
    private String arrivalAirport;
    private Time arrivalTime;
    private Date arrivalDate;
    private String longFlight;
    private String airlineName;
    private String flightClass;
    private String airlineCode;
    private Integer checkedBaggage;
    private Integer cabinBaggage;
    private Integer adultPrice;
    private Integer childPrice;
    private Integer infantPrice;
    private Integer totalPrice;

    public DetailFlight(String departureAirport, Time departureTime, Date departureDate, String arrivalAirport, Time arrivalTime, Date arrivalDate, String longFlight, String airlineName, String airlineCode, Integer checkedBaggage, Integer cabinBaggage, Integer adultPrice, Integer childPrice, Integer infantPrice) {
        this.departureAirport = departureAirport;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.arrivalAirport = arrivalAirport;
        this.arrivalTime = arrivalTime;
        this.arrivalDate = arrivalDate;
        this.longFlight = longFlight;
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
        this.checkedBaggage = checkedBaggage;
        this.cabinBaggage = cabinBaggage;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.infantPrice = infantPrice;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getLongFlight() {
        return longFlight;
    }

    public void setLongFlight(String longFlight) {
        this.longFlight = longFlight;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Integer getCheckedBaggage() {
        return checkedBaggage;
    }

    public void setCheckedBaggage(Integer checkedBaggage) {
        this.checkedBaggage = checkedBaggage;
    }

    public Integer getCabinBaggage() {
        return cabinBaggage;
    }

    public void setCabinBaggage(Integer cabinBaggage) {
        this.cabinBaggage = cabinBaggage;
    }

    public Integer getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(Integer adultPrice) {
        this.adultPrice = adultPrice;
    }

    public Integer getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(Integer childPrice) {
        this.childPrice = childPrice;
    }

    public Integer getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(Integer infantPrice) {
        this.infantPrice = infantPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
