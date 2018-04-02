package com.example.wangmengyun.Bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.wangmengyun.database.FlightBaseHelper;
import com.example.wangmengyun.database.FlightCursorWrapper;
import com.example.wangmengyun.database.FlightDbSchema;
import com.example.wangmengyun.database.FlightDbSchema.FlightTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by wangmengyun on 2018/3/28.
 *这里是参考安卓编程指南的CrimeLab
 *
 */

public class FlightLab {

    private static FlightLab sFlightLab;

    private Context mContext;

    private SQLiteDatabase mDatabase;

    public static FlightLab get(Context context){
        if(sFlightLab == null) {
            sFlightLab = new FlightLab(context);
        }
        return sFlightLab;
    }



    private FlightLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new FlightBaseHelper(mContext).getWritableDatabase();

    }

    public List<Flight> getFlight() {
        List<Flight> flights = new ArrayList<>();
        FlightCursorWrapper cursor = queryFlights(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                flights.add(cursor.getFlight());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }

        return flights;
    }

    public Flight getFlight(UUID id) {
        FlightCursorWrapper cursor = queryFlights(
                FlightTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getFlight();
        }
        finally {
            cursor.close();
        }
    }

    public void addFlight(Flight flight) {
        ContentValues values = getContentValues(flight);
        mDatabase.insert(FlightTable.NAME,null,values);

    }

    private static ContentValues getContentValues(Flight flight) {
        ContentValues values = new ContentValues();
        values.put(FlightTable.Cols.UUID,flight.getFlightNumber().toString());

        values.put(FlightTable.Cols.DEPARTCITY,flight.getDeparture_City());
        values.put(FlightTable.Cols.ARRIVECITY,flight.getArrive_City());
        values.put(FlightTable.Cols.PRICE,flight.getPrice());
        return values;
    }

    public void updateFlight(Flight flight) {
        String uuidString = flight.getFlightNumber().toString();
        ContentValues values = getContentValues(flight);
        mDatabase.update(FlightTable.NAME,values,FlightTable.Cols.UUID + " = ?",new String[] {uuidString});
    }

    private FlightCursorWrapper queryFlights(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                FlightTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new FlightCursorWrapper(cursor);
    }

    public void deleteFlight(UUID id) {
        mDatabase.delete(FlightDbSchema.FlightTable.NAME, FlightTable.Cols.UUID + " = ?", new String[] {id.toString()});
    }
}
