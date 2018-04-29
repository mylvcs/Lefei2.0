package com.example.wangmengyun.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.adapter.GithubRepoAdapter;
import com.example.wangmengyun.database.FavoriteGoodsEntity;
import com.example.wangmengyun.https.presenter.GithubRepo;
import com.example.wangmengyun.https.service.GithubClient;
import com.example.wangmengyun.lefei.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangmengyun on 2018/4/26.
 */

public class MyFavouriteActivity extends AppCompatActivity{
    private ListView listView;
    private static final String BASE_URL = "https://52.207.246.217/api/1/databases/mongo_connect/" ;

//    private static final String BASE_URL = "https://api.github.com";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


                setContentView(R.layout.activity_favorite);

                listView = (ListView) findViewById(R.id.pagination_list);


                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                GithubClient client = retrofit.create(GithubClient.class);

                Call<List<Flight>> call = client.getFlights("flights","l2vM_qRqK1SfqIbQsV9zq4PJVINybEvN");

                call.enqueue(new Callback<List<Flight>>() {

                    @Override
                    public void onResponse(Call<List<Flight>> call, Response<List<Flight>> response) {
                        List<Flight> flights = response.body();

                        listView.setAdapter(new GithubRepoAdapter(MyFavouriteActivity.this, flights));
                    }

                    @Override
                    public void onFailure(Call<List<Flight>> call, Throwable t) {
                        Toast.makeText(MyFavouriteActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
