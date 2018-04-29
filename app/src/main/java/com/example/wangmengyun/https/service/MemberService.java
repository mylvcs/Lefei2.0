//package com.example.wangmengyun.https.service;
//
//import com.example.wangmengyun.Bean.UserBean;
//
//import java.net.HttpRetryException;
//import java.util.Observable;
//
//import retrofit2.http.Field;
//import retrofit2.http.POST;
//
///**
// * Created by wangmengyun on 2018/4/27.
// */
//
//public interface MemberService {
//
//    //用户注册
//    @POST("member")
//    Observable<HttpResult<UserBean>> register(
//            @Field("name") String name,
//            @Field("password") String password,
//            @Field("email") String email);
//    //TODO
//    //登录 P103