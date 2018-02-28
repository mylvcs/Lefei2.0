package com.wangmengyun.lefei2;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by wangmengyun on 2018/2/28.
 */
//单例

public class FlightLab {
    private static FlightLab sFlightLab;

    private List<Flight> mFlights;

    public static FlightLab get(Context context) {
        if (sFlightLab == null) {
            sFlightLab = new FlightLab(context);

        }
        return sFlightLab;

    }

    private FlightLab(Context context) {

        mFlights = new ArrayList<>();
    }


    public List<Flight> getFlights(){
            return mFlights;
    }

    public Flight getFlight(UUID id) {
        for (Flight flight : mFlights) {
            if (flight.getmFlightID().equals(id)) {
                return flight;
            }
        }

        return null;
    }
}

