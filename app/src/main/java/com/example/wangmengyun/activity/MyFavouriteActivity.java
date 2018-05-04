package com.example.wangmengyun.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.wangmengyun.adapter.FavoriteGoodsListAdapter;
import com.example.wangmengyun.https.Entity.FavoriteGoodsEntity;
import com.example.wangmengyun.https.Entity.GoodsEntity;
import com.example.wangmengyun.https.ProgressDialogSubscriber;
import com.example.wangmengyun.https.presenter.GoodsPresenter;
import com.example.wangmengyun.lefei.R;
import com.firebase.ui.auth.ui.ProgressDialogHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by wangmengyun on 2018/4/26.
 */

public class MyFavouriteActivity extends  AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.product_list)
    RecyclerView productList;


    @BindView(R.id.favorite_index_btn)
    Button favorite_index_btn;

    @BindView(R.id.favorite_empty)
    LinearLayout favorite_empty;

    private FavoriteGoodsListAdapter adapter;
    private List<FavoriteGoodsEntity> data = new ArrayList<>();

    private ListView listView;
    private static final String BASE_URL = "http://192.0.0.1/members";

    //    private static final String BASE_URL = "https://api.github.com";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);

        SharedPreferences sp = getSharedPreferences("user", 0);
        int memberId = sp.getInt("member_Id", -1);


        adapter = new FavoriteGoodsListAdapter(this, data);

        productList.setAdapter(adapter);


//        adapter.setOnGoodsItemClickListener(new FavoriteGoodsListAdapter.OnGoodsItemClickListener() {
//
//            @Override
//            public void onClick(View view, FavoriteGoodsEntity entity) {
//                Intent intent = new Intent(MyFavouriteActivity.this, GoodsActivity.class);
//                intent.putExtra("goodsId", entity.getId());
//                startActivity(intent);
//            }
//        });
    }

    @OnClick({R.id.title_back, R.id.favorite_index_btn})

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.favorite_index_btn:
                Intent intent = new Intent(this, MainActivity.class);

                startActivity(intent);
                finish();
        }

    }


    private void requestList(int memberId){
        if (memberId!= -1)
            GoodsPresenter.getFavoriteList(new ProgressDialogSubscriber<List<FavoriteGoodsEntity>>(this){



                @Override
                public void onNext(List<FavoriteGoodsEntity> favoriteGoodsEntities) {

                    if (favoriteGoodsEntities.size() == 0) {
                        productList.setVisibility(View.GONE);

                        favorite_empty.setVisibility(View.VISIBLE);

                    } else {
                        favorite_empty.setVisibility(View.GONE);
                        productList.setVisibility(View.VISIBLE);

                        data.addAll(favoriteGoodsEntities);

                        adapter.notifyDataSetChanged();
                    }
                }

            }, memberId);
    }


//                Retrofit.Builder builder = new Retrofit.Builder()
//                        .baseUrl(BASE_URL)
//                        .addConverterFactory(GsonConverterFactory.create());
//
//                Retrofit retrofit = builder.build();
//
//                GithubClient client = retrofit.create(GithubClient.class);
//
//                Call<List<Flight>> call = client.getFlights("flights","l2vM_qRqK1SfqIbQsV9zq4PJVINybEvN");
//
//                call.enqueue(new Callback<List<Flight>>() {
//
//                    @Override
//                    public void onResponse(Call<List<Flight>> call, Response<List<Flight>> response) {
//                        List<Flight> flights = response.body();
//
//                        listView.setAdapter(new GithubRepoAdapter(MyFavouriteActivity.this, flights));
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Flight>> call, Throwable t) {
//                        Toast.makeText(MyFavouriteActivity.this, "error :(", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }

}
