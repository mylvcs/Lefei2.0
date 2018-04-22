package com.example.wangmengyun.MongoLab;

/**
 * Created by wangmengyun on 2018/4/22.
 */

class MyFlight {
    public String _id;
    public String stops;
    public String ticket_price;
    public String departure;
    public String arrival;
    public String airline;
    public String plain;
    public String timings;
    public String flight_duration;


    public String get_id() {
        return _id;
    }

    public String getStops() {
        return stops;
    }

    public String getTicket_price() {
        return ticket_price;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getAirline() {
        return airline;
    }

    public String getPlain() {
        return plain;
    }

    public String getTimings() {
        return timings;
    }

    public String getFlight_duration() {
        return flight_duration;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public void setTicket_price(String ticket_price) {
        this.ticket_price = ticket_price;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public void setFlight_duration(String flight_duration) {
        this.flight_duration = flight_duration;
    }
}
