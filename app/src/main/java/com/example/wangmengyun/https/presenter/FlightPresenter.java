package com.example.wangmengyun.https.presenter;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.Bean.HttpResult;
import com.example.wangmengyun.https.Entity.FavoriteGoodsEntity;
import com.example.wangmengyun.https.Entity.GoodsEntity;
import com.example.wangmengyun.https.HttpMethods;
import com.example.wangmengyun.https.service.FlightService;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

import static com.example.wangmengyun.https.HttpMethods.flightService;
import static com.example.wangmengyun.https.HttpMethods.toSubscribe;

/**
 * Created by wangmengyun on 2018/5/4.
 */

public class FlightPresenter {

    /**
     * 获取列表
     * @param subscriber
     * @param catId
     */

    public static void list(Subscriber<List<Flight>> subscriber, int catId){
        Observable<List<Flight>> observable = flightService.list(catId).map(
                new HttpMethods.HttpResultFunc<List<Flight>>());

        toSubscribe(observable, subscriber);
    }

    /**
     * 获取列表
     * @param subscriber
     * @param flightId
     */

    public static void goodsDetail(Subscriber<List<Flight>> subscriber, int flightId){
        Observable<List<Flight>> observable = flightService.list(flightId).map(
                new HttpMethods.HttpResultFunc<List<Flight>>());

        toSubscribe(observable, subscriber);
    }


    /**
     * 添加到购物车
     * @param subscriber
     * @param flightId
     * @param memberId
     */

    public static void addToCart(Subscriber<HttpResult> subscriber, int flightId, int memberId){
        Observable<HttpResult> observable = flightService.addToCart(flightId, memberId);

        toSubscribe(observable, subscriber);
    }

    /**
     * 添加到收藏
     * @param subscriber
     * @param memberId
     * @param flightId
     */

    public static void addToFavourite(Subscriber<HttpResult> subscriber, int memberId, int flightId){
        Observable<HttpResult> observable = flightService.addToFavorite(memberId, flightId);

        toSubscribe(observable, subscriber);
    }

    /**
     * 获取收藏列表
     * @param subscriber

     * @param memberId
     */

    public static void getFavoriteList(Subscriber<List<FavoriteGoodsEntity>> subscriber, int memberId){
        Observable<List<FavoriteGoodsEntity>> observable = flightService.favourieList(memberId)
                .map(new HttpMethods.HttpResultFunc<List<FavoriteGoodsEntity>>());

        toSubscribe(observable, subscriber);
    }
}