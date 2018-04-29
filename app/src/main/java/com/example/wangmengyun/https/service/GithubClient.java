package com.example.wangmengyun.https.service;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.https.presenter.GithubRepo;
import com.google.firebase.auth.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wangmengyun on 2018/4/28.
 */

public interface GithubClient {

    @GET("/collections/{collections}/?apiKey=/{apiKey}" )
    Call<List<Flight>> getFlights(
            @Path("collections") String collections,
            @Query("apiKey") String apiKey

    );

}
