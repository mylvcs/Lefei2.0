package com.example.wangmengyun.https.presenter;

import com.example.wangmengyun.Bean.HttpResult;
import com.example.wangmengyun.https.Entity.FavoriteGoodsEntity;
import com.example.wangmengyun.https.HttpMethods;
import com.example.wangmengyun.https.Entity.GoodsEntity;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by wangmengyun on 2018/5/1.
 */

public class GoodsPresenter extends HttpMethods{

    /**
     * 获取列表
     * @param subscriber
     * @param catId
     */

    public static void list(Subscriber<List<GoodsEntity>> subscriber, int catId){
        Observable<List<GoodsEntity>> observable = goodsService.list(catId).map(
                new HttpResultFunc<List<GoodsEntity>>());

                toSubscribe(observable, subscriber);
    }

    /**
     * 获取列表
     * @param subscriber
     * @param goodsId
     */

    public static void goodsDetail(Subscriber<List<GoodsEntity>> subscriber, int goodsId){
        Observable<List<GoodsEntity>> observable = goodsService.list(goodsId).map(
                new HttpResultFunc<List<GoodsEntity>>());

        toSubscribe(observable, subscriber);
    }


    /**
     * 添加到购物车
     * @param subscriber
     * @param goodsId
     * @param memberId
     */

    public static void addToCart(Subscriber<HttpResult> subscriber, int goodsId, int memberId){
        Observable<HttpResult> observable = goodsService.addToCart(goodsId, memberId);

        toSubscribe(observable, subscriber);
    }

    /**
     * 添加到收藏
     * @param subscriber
     * @param memberId
     * @param goodsId
     */

    public static void addToFavourite(Subscriber<HttpResult> subscriber, int memberId, int goodsId){
        Observable<HttpResult> observable = goodsService.addToFavorite(memberId, goodsId);

        toSubscribe(observable, subscriber);
    }

    /**
     * 获取收藏列表
     * @param subscriber

     * @param memberId
     */

    public static void getFavoriteList(Subscriber<List<FavoriteGoodsEntity>> subscriber, int memberId){
        Observable<List<FavoriteGoodsEntity>> observable = goodsService.favourieList(memberId)
                .map(new HttpResultFunc<List<FavoriteGoodsEntity>>());

        toSubscribe(observable, subscriber);
    }
}
