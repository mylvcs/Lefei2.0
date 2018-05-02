package com.example.wangmengyun.https.presenter;


import com.example.wangmengyun.https.Entity.MemberEntity;
import com.example.wangmengyun.https.HttpMethods;


import rx.Observable;
import rx.Subscriber;

/**
 * Created by wangmengyun on 2018/4/27.
 */

public class MemberPresenter extends HttpMethods {

    public static void login(Subscriber<MemberEntity> subscriber, String username, String password){
        Observable observable = memberService.login(username,password).map(new HttpResultFunc<MemberEntity>());

        toSubscribe(observable,subscriber);
    }

    public static void register(Subscriber<MemberEntity> subscriber, String username, String password){
        Observable observable = memberService.login(username,password).map(new HttpResultFunc<MemberEntity>());

        toSubscribe(observable,subscriber);
    }
}
