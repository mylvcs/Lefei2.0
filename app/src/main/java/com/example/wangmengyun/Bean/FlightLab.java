package com.example.wangmengyun.Bean;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by wangmengyun on 2018/3/28.
 *这里是参考安卓编程指南的CrimeLab
 *
 */

public class FlightLab {
    private static FlightLab sflightLab;

    private List<Flight> mFlights;

    public static FlightLab get(Context context) {
        if (sflightLab ==null){
            sflightLab = new FlightLab(context);
        }
        return sflightLab;

    }


    private FlightLab(Context context) {

        mFlights = new ArrayList<>();

        for(int i=0;i<10; i++){
            Flight flight = new Flight();
            flight.setFlightNumber(i);
            flight.setArrive_City("Shanghai");
            flight.setDeparture_City("Beijing");
            flight.setDeparture_Date("2018/02/02");
            flight.setArrive_Date("2018/02/03");
            mFlights.add(flight);
        }
        for(int i=0;i<10; i++){
            Flight flight = new Flight();
            flight.setFlightNumber(i);
            flight.setArrive_City("Shanghai");
            flight.setDeparture_City("Shanghai");
            flight.setDeparture_Date("2018/02/02");
            flight.setArrive_Date("2018/02/03");
            mFlights.add(flight);
        }
        for(int i=0;i<10; i++){
            Flight flight = new Flight();
            flight.setFlightNumber(i);
            flight.setArrive_City("Shanghai");
            flight.setDeparture_City("Guangzhou");
            flight.setDeparture_Date("2018/02/02");
            flight.setArrive_Date("2018/02/03");
            mFlights.add(flight);
        }
        for(int i=0;i<10; i++){
            Flight flight = new Flight();
            flight.setFlightNumber(i);
            flight.setArrive_City("Shanghai");
            flight.setDeparture_City("Nanjing");
            flight.setDeparture_Date("2018/02/02");
            flight.setArrive_Date("2018/02/03");
            mFlights.add(flight);
        }


    }

    public List<Flight> getFlights() {
        return mFlights;

    }

//    public Flight getFlight(UUID id){
//        for (Flight flight:mFlights){
//            if (flight.getId().equals(id)){
//
//            }
//        }
//            return null;

}
