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
        if (sflightLab == null) {
            sflightLab = new FlightLab(context);
        }
        return sflightLab;

    }


    private FlightLab(Context context) {

        mFlights = new ArrayList<>();


    }

    public List<Flight> getFlights() {
        return mFlights;

    }

    public Flight getFlight(UUID mId) {

        for (Flight flight : mFlights) {
            if (flight.getFlightNumber().equals(mId)) {
                return flight;
            }

        }
        return null;
    }

}