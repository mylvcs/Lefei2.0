package com.example.wangmengyun.Bean;

import java.util.Date;
import java.util.UUID;

/**
 * Created by poornima-udacity on 6/26/15.
 * 航班类里有航班号，出发城市，到达城市，出发时间，到达时间，核载人数，出发日期
 */
public class Flight {
    public UUID flightNumber;
    public String departure_City;
    public String arrive_City;
    public String departure_Date;
    public String arrive_Date;
    public Date date;
    public String price;
   
//    public Flight(String departure_city, String arrive_city, String departure_date, String arrive_date)
//    {
//        this.departure_City = departure_city;
//        this.arrive_City = arrive_city;
//        this.departure_Date = departure_date;
//        this.arrive_Date = arrive_date;
//    }

    public Flight(UUID id) {
        flightNumber = id;
        date = new Date();
    }
    
    public Flight() {
        this(UUID.randomUUID());
    }

    public Flight(String DepartureCity){
        departure_City = DepartureCity;
        date = new Date();
        flightNumber = UUID.randomUUID();

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

    public String getDeparture_City() {
        return departure_City;
    }

    public void setDeparture_City(String departure_City) {
        this.departure_City = departure_City;
    }

    public String getArrive_City() {
        return arrive_City;
    }

    public void setArrive_City(String arrive_City) {
        this.arrive_City = arrive_City;
    }

    public String getDeparture_Date() {
        return departure_Date;
    }

    public void setDeparture_Date(String departure_Date) {
        this.departure_Date = departure_Date;
    }

    public String getArrive_Date() {
        return arrive_Date;
    }

    public void setArrive_Date(String arrive_Date) {
        this.arrive_Date = arrive_Date;
    }
}