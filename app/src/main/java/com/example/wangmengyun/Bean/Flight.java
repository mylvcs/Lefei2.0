package com.example.wangmengyun.Bean;

import java.util.Date;
import java.util.UUID;

/**
 * 航班类里有航班号，出发城市，到达城市，出发时间，到达时间，出发日期, airlineCompany, 机票价格
 * 是否直飞，飞机号，所用飞机型号，出发机场，到达机场，飞行时间。
 */
public class Flight {
    public UUID flightNumber;
    public String departure;
    public String arrival;
    public String departure_time;
    public String arrival_time;
    public Date date;
    public String price;
    public String airline;
    public String stops ;
    public String plane;
    public String departure_airport;
    public String flight_duration;
    public String arrival_airport;
    public String plane_code;

    public String parsedFlightData;

    public Flight(UUID id) {
        flightNumber = id;
        date = new Date();
    }
    
    public Flight() {
        this(UUID.randomUUID());
    }


    public Date getDate() {

        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public UUID getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(UUID flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getFlight_duration() {
        return flight_duration;
    }

    public void setFlight_duration(String flight_duration) {
        this.flight_duration = flight_duration;
    }

    public String getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public String getPlane_code() {
        return plane_code;
    }

    public void setPlane_code(String plane_code) {
        this.plane_code = plane_code;
    }

    public String getParsedFlightData() {
        return parsedFlightData;
    }

    public void setParsedFlightData(String parsedFlightData) {
        this.parsedFlightData = parsedFlightData;
    }
}