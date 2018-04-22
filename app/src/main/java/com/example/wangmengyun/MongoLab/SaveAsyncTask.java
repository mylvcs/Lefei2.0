package com.example.wangmengyun.MongoLab;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;


public class SaveAsyncTask extends AsyncTask<MyFlight, Void, Boolean> {

	@Override
	protected Boolean doInBackground(MyFlight... arg0) {
		try 
		{			
			MyFlight flight = arg0[0];
			
			QueryBuilder qb = new QueryBuilder();						
			
			HttpClient httpClient = new DefaultHttpClient();
	        HttpPost request = new HttpPost(qb.buildContactsSaveURL());

	        StringEntity params =new StringEntity(qb.createArrayList(flight));
	        request.addHeader("content-type", "application/json");
	        request.setEntity(params);
	        HttpResponse response = httpClient.execute(request);
	        
	        if(response.getStatusLine().getStatusCode()<205)
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
		} catch (Exception e) {
			//e.getCause();
			String val = e.getMessage();
			String val2 = val;
			return false;
		}		
	}

}
