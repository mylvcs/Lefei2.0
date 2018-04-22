package com.example.wangmengyun.data;

/**
 * Created by wangmengyun on 2018/4/22.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Bean.FlightContract;

public class FlightDBHelper extends SQLiteOpenHelper {

    
    public static final String DATABASE_NAME = "flights.db";


    private static final int DATABASE_VERSION = 3;

    public FlightDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the creation of
     * tables and the initial population of the tables should happen.
     *
     * @param sqLiteDatabase The database.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_WEATHER_TABLE =

                "CREATE TABLE " + FlightContract.FlightEntry.TABLE_NAME + " (" +
                        
                        FlightContract.FlightEntry._ID               + " INTEGER PRIMARY KEY AUTOINCREMENT, " +


                        FlightContract.FlightEntry.COLUMN_WEATHER_ID + " INTEGER NOT NULL,"                  +

                        FlightContract.FlightEntry.COLUMN_HUMIDITY   + " REAL NOT NULL, "                    +
                        FlightContract.FlightEntry.COLUMN_PRESSURE   + " REAL NOT NULL, "                    +

                        FlightContract.FlightEntry.COLUMN_WIND_SPEED + " REAL NOT NULL, "                    +
                        FlightContract.FlightEntry.COLUMN_DEGREES    + " REAL NOT NULL, "                    +

                        " UNIQUE (" + FlightContract.FlightEntry.COLUMN_WEATHER_ID + ") ON CONFLICT REPLACE);";


        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    /**
     * This database is only a cache for online data, so its upgrade policy is simply to discard
     * the data and call through to onCreate to recreate the table. Note that this only fires if
     * you change the version number for your database (in our case, DATABASE_VERSION). It does NOT
     * depend on the version number for your application found in your app/build.gradle file. If
     * you want to update the schema without wiping data, commenting out the current body of this
     * method should be your top priority before modifying this method.
     *
     * @param sqLiteDatabase Database that is being upgraded
     * @param oldVersion     The old database version
     * @param newVersion     The new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FlightContract.FlightEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}