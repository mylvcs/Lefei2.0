package com.example.wangmengyun.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.wangmengyun.database.FlightDbSchema.FlightTable;
import com.example.wangmengyun.Bean.Flight;

import java.util.Date;
import java.util.UUID;


public class FlightCursorWrapper extends CursorWrapper {
    public FlightCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Flight getFlight() {
        String flightNumber = getString(getColumnIndex(FlightTable.Cols.UUID));
        String Departure_City = getString(getColumnIndex(FlightTable.Cols.DEPARTCITY));
        String Arrive_City = getString(getColumnIndex(FlightTable.Cols.ARRIVECITY));


         String price = getString(getColumnIndex(FlightTable.Cols.PRICE));

        Flight flight = new Flight(UUID.fromString(flightNumber));

        flight.setDeparture(Departure_City);

        flight.setArrival(Arrive_City);
        flight.setPrice(price);

        return flight;
    }
}
