package com.example.wangmengyun.https;

import android.util.Log;

import com.example.wangmengyun.Bean.HttpResult;
import com.example.wangmengyun.database.Category;
import com.example.wangmengyun.https.service.CategoryService;
import com.example.wangmengyun.https.service.FlightService;
import com.example.wangmengyun.https.service.GoodsService;
import com.example.wangmengyun.https.service.MemberService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wangmengyun on 2018/4/30.
 */

public class HttpMethods {

    public static GoodsService goodsService;

    public static CategoryService categoryService;

    protected static final String BASE_URL = "http://39.108.51.161:3000/flights";

    private static final String TAG = "HttpMethods";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private static HttpMethods mInstance;
    public static FlightService flightService;

    protected static MemberService memberService;

    public HttpMethods() {

        if (mInstance == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient).build();

            memberService = retrofit.create(MemberService.class);

            goodsService = retrofit.create(GoodsService.class);

            categoryService= retrofit.create(CategoryService.class);

            flightService = retrofit.create(FlightService.class);

        }
    }

    public static HttpMethods getInstance() {
        if (mInstance == null) {
            synchronized (HttpMethods.class) {
                if (mInstance == null) {
                    mInstance = new HttpMethods();
                }
            }
        }

        return mInstance;
    }

    public static class HttpResultFunc<T> implements Func1<HttpResult<T>,T>{

        @Override
        public T call(HttpResult<T> httpResult) {
            Log.i(TAG, "status:" + httpResult.getStatus());

            Log.i(TAG, "msg:" + httpResult.getMsg());
            Log.i(TAG, "status:" + httpResult.getData());
            return httpResult.getData();
        }
    }

    public static <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(s);

    }

}

