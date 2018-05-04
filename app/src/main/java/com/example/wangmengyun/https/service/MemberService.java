package com.example.wangmengyun.https.service;

import com.example.wangmengyun.Bean.HttpResult;
import com.example.wangmengyun.Bean.UserBean;

import com.example.wangmengyun.https.Entity.MemberEntity;




import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wangmengyun on 2018/4/27.
 */

public interface MemberService {

    //用户注册
    @POST("member")
    Observable<HttpResult<MemberEntity>> register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email);

    @FormUrlEncoded
    @POST("member/login")
    Observable<HttpResult<MemberEntity>> login(
            @Field("username") String username,
            @Field("password") String password

    );
}