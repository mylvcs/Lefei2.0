//package com.example.wangmengyun.https;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Created by wangmengyun on 2018/4/27.
// */
//
//public class ServiceGenerator {
//
//    protected static final String B_URL = "https://api.mlab.com/api/1/databases/mongo_connect/collections/flights/?&apiKey=l2vM_qRqK1SfqIbQsV9zq4PJVINybEvN";
//
//    private static final int DEFAULT_TIMEOUT = 5;
//
//
// //   private static final String BASE_URL = "https://api.github.com/";
//
//    private static Retrofit.Builder builder =
//            new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create());
//
//    private static Retrofit retrofit = builder.build();
//
//    private static HttpLoggingInterceptor logging =
//            new HttpLoggingInterceptor()
//                    .setLevel(HttpLoggingInterceptor.Level.BODY);
//
//    private static OkHttpClient.Builder httpClient =
//            new OkHttpClient.Builder();
//
//    public static <S> S createService(
//            Class<S> serviceClass) {
//        if (!httpClient.interceptors().contains(logging)) {
//            httpClient.addInterceptor(logging);
//            builder.client(httpClient.build());
//            retrofit = builder.build();
//        }
//
//        return retrofit.create(serviceClass);
//    }
//}
//
