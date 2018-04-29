package com.example.wangmengyun.activity;

import android.view.inputmethod.InputContentInfo;

import org.apache.http.HttpConnection;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by wangmengyun on 2018/4/29.
 */

public class HttpDataHandler {

    static String stream = null;

    public HttpDataHandler(){

    }

    public String GetHttpData (String urlString){
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if(urlConnection.getResponseCode() ==200){
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = r.readLine())!=null){
                    sb.append(line);
                    stream = sb.toString();
                    urlConnection.disconnect();
                }
            }else {

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream;
    }


    public String PostHttpData (String urlString, String json){
        try{
            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            urlConnection.setFixedLengthStreamingMode(length);

            urlConnection.setRequestProperty("Content-Type", "application/json; charset= UTF-8");

            urlConnection.connect();
            try ( OutputStream os = urlConnection.getOutputStream()){

                os.write(out);
            }
            InputStream response= urlConnection.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }


    public String PUTHttpData (String urlString, String newValue){
        try{
            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("PUT");
            urlConnection.setDoOutput(true);

            byte[] out = newValue.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            urlConnection.setFixedLengthStreamingMode(length);

            urlConnection.setRequestProperty("Content-Type", "application/json; charset= UTF-8");

            urlConnection.connect();
            try ( OutputStream os = urlConnection.getOutputStream()){

                os.write(out);
            }
            InputStream response= urlConnection.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream;
    }

}
