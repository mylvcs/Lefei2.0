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
    Observable<HttpResult<UserBean>> register(
            @Field("name") String name,
            @Field("password") String password,
            @Field("email") String email);

    //TODO
    //登录 P103
    @FormUrlEncoded
    @POST("member/login")
    Observable<HttpResult<MemberEntity>> login(
            @Field("name") String username,
            @Field("password") String password

    );
}