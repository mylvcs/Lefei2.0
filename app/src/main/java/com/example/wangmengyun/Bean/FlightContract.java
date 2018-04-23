package com.example.wangmengyun.Bean;

/**
 * Created by wangmengyun on 2018/3/24.
 */

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.wangmengyun.Utils.SunshineDateUtils;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.wangmengyun.Utils.SunshineDateUtils;


/**
 * Defines table and column names for the weather database. This class is not necessary, but keeps
 * the code organized.
 */
public class FlightContract {

    /*
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website. A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * Play Store.
     */
   public static final String CONTENT_AUTHORITY = "api.mongolab.com/api/1/databases/mongo_connect/collections/flights?apiKey=l2vM_qRqK1SfqIbQsV9zq4PJVINybEvN";

//    public static final String CONTENT_AUTHORITY = "com.example.wangmengyun.lefei";
    /*
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider for Sunshine.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);



    public static final class FlightEntry implements BaseColumns {

        /* The base CONTENT_URI used to query the Weather table from the content provider */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().build();

        /* Used internally as the name of our weather table. */
        public static final String TABLE_NAME = "flights";

        /*


        /* Weather ID as returned by API, used to identify the icon to be used */
        public static final String COLUMN_WEATHER_ID = "flight_id";

        /* Humidity is stored as a float representing percentage */
        public static final String COLUMN_HUMIDITY = "departure";

        /* Pressure is stored as a float representing percentage */
        public static final String COLUMN_PRESSURE = "arrival";

        /* Wind speed is stored as a float representing wind speed in mph */
        public static final String COLUMN_WIND_SPEED = "ticket_price";

        public static final String COLUMN_DEGREES = "airlines";


        public static Uri buildFlightUriWithdeparture(String departure) {
            return CONTENT_URI.buildUpon()
                    .appendPath(departure)
                    .build();
        }


        public static String getSqlSelectForTodayOnwards() {
            long normalizedUtcNow = SunshineDateUtils.normalizeDate(System.currentTimeMillis());
            return com.example.wangmengyun.data.WeatherContract.WeatherEntry.COLUMN_DATE + " >= " + normalizedUtcNow;
        }
    }
}