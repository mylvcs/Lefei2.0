package com.example.wangmengyun.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.database.FlightDbSchema.FlightTable;


public class FlightBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "flightBase.db";

    public FlightBaseHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                    "create table "+ FlightTable.NAME +
                    "(" +
                    "_id integer primary key autoincrement," +
                            FlightTable.Cols.UUID + "," +

                            FlightTable.Cols.DEPARTCITY + "," +
                            FlightTable.Cols.ARRIVECITY + "," +
                            FlightTable.Cols.PRICE +
                    ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
