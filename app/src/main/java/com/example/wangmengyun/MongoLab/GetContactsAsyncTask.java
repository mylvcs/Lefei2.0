package com.example.wangmengyun.MongoLab;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


/**
 * Async Task to retrieve your stored contacts from mongolab
 *
 */
public class GetContactsAsyncTask extends AsyncTask<MyFlight, Void, ArrayList<MyFlight>> {
	static BasicDBObject user = null;
	static String OriginalObject = "";
	static String server_output = null;
	static String temp_output = null;

	@Override
	protected ArrayList<MyFlight> doInBackground(MyFlight... arg0) {
		
		ArrayList<MyFlight> flightArrayList = new ArrayList<MyFlight>();
		try 
		{			
			
			QueryBuilder qb = new QueryBuilder();						
	        URL url = new URL(qb.buildContactsGetURL());
	        HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
	        conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			while ((temp_output = br.readLine()) != null) {
				server_output = temp_output;
			}
			
            // create a basic db list
			String mongoarray = "{ artificial_basicdb_list: "+server_output+"}";
			Object o = com.mongodb.util.JSON.parse(mongoarray);
			

			DBObject dbObj = (DBObject) o;
			BasicDBList contacts = (BasicDBList) dbObj.get("artificial_basicdb_list");
			
		  for (Object obj : contacts) {
			DBObject userObj = (DBObject) obj; 
			
			MyFlight temp = new MyFlight();
			temp.set_id(userObj.get("flight_id").toString());
			temp.setDeparture(userObj.get("Departure").toString());
			temp.setArrival(userObj.get("Arrival").toString());
			temp.setTicket_price(userObj.get("Ticket_price").toString());

			flightArrayList.add(temp);
			
			}
		
		}catch (Exception e) {
			e.getMessage();
		}
		
		return flightArrayList;
	}
}
