package com.example.wangmengyun.activity;


import com.example.wangmengyun.data.User;

/**
 * Created by wangmengyun on 2018/4/29.
 */

public class Common {
    private static String DB_NAME = "mongo_connect";
    private static String COLLECTION = "users";

    public static String API_KEY= "l2vM_qRqK1SfqIbQsV9zq4PJVINybEvN";

    public static String getAddressSingle(User user){
        String baseURL = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_NAME, COLLECTION);

        StringBuilder stringBuilder = new StringBuilder(baseURL);
        stringBuilder.append("/?apiKey=" +API_KEY);

        return stringBuilder.toString();
    }

    public static String getAddressAPI(){
        String baseURL = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s", DB_NAME, COLLECTION);

        StringBuilder stringBuilder = new StringBuilder(baseURL);
        stringBuilder.append("?apiKey=" +API_KEY);

        return stringBuilder.toString();
    }

}
