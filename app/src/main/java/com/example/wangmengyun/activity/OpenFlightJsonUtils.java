package com.example.wangmengyun.activity;

/**
 * Created by wangmengyun on 2018/4/23.
 */

import android.content.Context;

import com.example.wangmengyun.Bean.Flight;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.util.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.List;

public final class OpenFlightJsonUtils {

    private static final String FLIGHTID = "id";
    private static final String LIST = "list";

    private static final String DEPARTURE = "departure";
    private static final String ARRIVAL = "arrival" ;


    public static String[] getJsonData(Context context, String JsonStr)
            throws JSONException {

        String parsedFlightData[];

        JSONArray flightArray  = new JSONArray(JsonStr);


        parsedFlightData = new String[flightArray.length()];

        for (int i = 0; i < flightArray.length(); i++) {

            JSONObject flight = flightArray.getJSONObject(i);

            String flightId = flight.getString("airline");

            String departure = flight.getString("departure");
            String arrival = flight.getString("arrival");

            parsedFlightData[i] = flightId + " - " + departure + " - " + arrival;
        }

        return parsedFlightData;
    }

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the weather over various days from the forecast.
     * <p/>
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *
     * @param forecastJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
//    public static ContentValues[] getWeatherContentValuesFromJson(Context context, String forecastJsonStr)
//            throws JSONException {
//
//        JSONObject forecastJson = new JSONObject(forecastJsonStr);
//
//        /* Is there an error? */
//        if (forecastJson.has(OWM_MESSAGE_CODE)) {
//            int errorCode = forecastJson.getInt(OWM_MESSAGE_CODE);
//
//            switch (errorCode) {
//                case HttpURLConnection.HTTP_OK:
//                    break;
//                case HttpURLConnection.HTTP_NOT_FOUND:
//                    /* Location invalid */
//                    return null;
//                default:
//                    /* Server probably down */
//                    return null;
//            }
//        }
//
//        JSONArray jsonWeatherArray = forecastJson.getJSONArray(OWM_LIST);
//
//        JSONObject cityJson = forecastJson.getJSONObject(OWM_CITY);
//
//        JSONObject cityCoord = cityJson.getJSONObject(OWM_COORD);
//        double cityLatitude = cityCoord.getDouble(OWM_LATITUDE);
//        double cityLongitude = cityCoord.getDouble(OWM_LONGITUDE);
//
//        SunshinePreferences.setLocationDetails(context, cityLatitude, cityLongitude);
//
//        ContentValues[] weatherContentValues = new ContentValues[jsonWeatherArray.length()];
//
//
//        long normalizedUtcStartDay = SunshineDateUtils.getNormalizedUtcDateForToday();
//
//        for (int i = 0; i < jsonWeatherArray.length(); i++) {
//
//            long dateTimeMillis;
//            double pressure;
//            int humidity;
//            double windSpeed;
//            double windDirection;
//
//            double high;
//            double low;
//
//            int weatherId;
//
//            /* Get the JSON object representing the day */
//            JSONObject dayForecast = jsonWeatherArray.getJSONObject(i);
//
//            /*
//             * We ignore all the datetime values embedded in the JSON and assume that
//             * the values are returned in-order by day (which is not guaranteed to be correct).
//             */
//            dateTimeMillis = normalizedUtcStartDay + SunshineDateUtils.DAY_IN_MILLIS * i;
//
//            pressure = dayForecast.getDouble(OWM_PRESSURE);
//            humidity = dayForecast.getInt(OWM_HUMIDITY);
//            windSpeed = dayForecast.getDouble(OWM_WINDSPEED);
//            windDirection = dayForecast.getDouble(OWM_WIND_DIRECTION);
//
//            /*
//             * Description is in a child array called "weather", which is 1 element long.
//             * That element also contains a weather code.
//             */
//            JSONObject weatherObject =
//                    dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
//
//            weatherId = weatherObject.getInt(OWM_WEATHER_ID);
//
//            /*
//             * Temperatures are sent by Open Weather Map in a child object called "temp".
//             *
//             * Editor's Note: Try not to name variables "temp" when working with temperature.
//             * It confuses everybody. Temp could easily mean any number of things, including
//             * temperature, temporary variable, temporary folder, temporary employee, or many
//             * others, and is just a bad variable name.
//             */
//            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
//            high = temperatureObject.getDouble(OWM_MAX);
//            low = temperatureObject.getDouble(OWM_MIN);
//
//            ContentValues weatherValues = new ContentValues();
//            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_DATE, dateTimeMillis);
//            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, humidity);
//            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, pressure);
//            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, windSpeed);
//            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_DEGREES, windDirection);
//            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP, high);
//            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP, low);
//            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, weatherId);
//
//            weatherContentValues[i] = weatherValues;
//        }
//
//        return weatherContentValues;
//    }
}