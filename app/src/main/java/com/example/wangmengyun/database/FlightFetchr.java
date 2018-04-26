package com.example.wangmengyun.database;

import android.net.Uri;
import android.util.Log;

import com.example.wangmengyun.Bean.Flight;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangmengyun on 2018/4/25.
 */

public class FlightFetchr {

    private static final String TAG = "FlickrFetchr";


    List<Flight> items = new ArrayList<>();

//    private static final Uri ENDPOINT = Uri.parse("https://api.mlab.com/api/1/databases/mongo_connect/collections/flights/?&apiKey=l2vM_qRqK1SfqIbQsV9zq4PJVINybEvN")
//            .buildUpon()
//            .build();


//    private String buildUrl(String method, String query) {
//        Uri.Builder uriBuilder = ENDPOINT.buildUpon()
//                .appendQueryParameter("method", method);
//
//        if (method.equals(SEARCH_METHOD)) {
//            uriBuilder.appendQueryParameter("text", query);
//        }

//        return uriBuilder.build().toString();


    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " +
                        urlSpec);
            }

            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

//        public List<GalleryItem.GalleryList.Photo> downloadGalleryItems(String url) {
//
//            List<GalleryItem.GalleryList.Photo> items = new ArrayList<>();
//
//            Log.i(TAG, "Making URL: " + url);
//            try{
//
//                String jsonString = getUrlString(url);
//                Log.i(TAG, "Received JSON: " + jsonString);
//
//                Gson gson = new Gson();
//                GalleryItem itemG = gson.fromJson(jsonString, GalleryItem.class);
//                GalleryItem.GalleryList list = itemG.getGalleryList();
//
//                items = list.getPhotos();
//
//                JSONObject jsonBody = new JSONObject(jsonString);
//
//                parseItems(item, jsonBody);
//
//            } catch (JSONException je) {
//                Log.e(TAG, "Failed to parse JSON", je);
//            } catch (IOException ioe) {
//                Log.e(TAG, "Failed to fetch items", ioe);
//            }
//            return items;
//        }

    public List<Flight> fetchItems() {
        try {
            String url = Uri.parse("https://api.mlab.com/api/1/databases/mongo_connect/collections/flights/?&apiKey=l2vM_qRqK1SfqIbQsV9zq4PJVINybEvN").
                    buildUpon().build().toString();

            String jsonString = getUrlString(url);

            Log.i(TAG, jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            parseItems(items, jsonArray);

        } catch (IOException e) {
            e.printStackTrace();

            Log.e(TAG, "Failed to fetch things", e);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return items;
    }

    private void parseItems(List<Flight> items, JSONArray jsonArray) throws IOException, JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {

            Flight item =new Flight();

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            item.setDeparture(jsonObject.getString("departure"));

            item.setArrival(jsonObject.getString("arrival"));

            item.setAirline(jsonObject.getString("airline"));

            item.setFlight_duration(jsonObject.getString("flight duration"));


            item.setPrice("$" + jsonObject.getString("ticket price"));

                JSONArray array = jsonObject.getJSONArray("timings");

                    JSONObject obj = array.getJSONObject(0);

                    String departure_airport = obj.getString("departure_airport");

                    String departure_time = obj.getString("departure_time");

                    String arrival_airport = obj.getString("arrival_airport");

                    String arrival_time = obj.getString("arrival_time");

                    item.setDeparture_time(departure_time);

                    item.setArrival_time(arrival_time);

                    item.setDeparture_airport(departure_airport);

                    item.setArrival_airport(arrival_airport);

                    item.setParsedFlightData(departure_time + " - " + arrival_time);

            items.add(item);
        }
    }
}





