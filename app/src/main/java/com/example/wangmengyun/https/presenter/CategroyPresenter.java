package com.example.wangmengyun.https.presenter;

import com.example.wangmengyun.data.Goods;
import com.example.wangmengyun.https.Entity.GoodsEntity;
import com.example.wangmengyun.https.HttpMethods;

import java.util.List;


import rx.Observable;
import rx.Subscriber;

/**
 * Created by wangmengyun on 2018/5/1.
 */

public class CategroyPresenter extends HttpMethods{


    public static void list(Subscriber<List<GoodsEntity>> subscriber,int catId){
        Observable<List<GoodsEntity>> observable = goodsService.list(catId).map(new HttpResultFunc<List<GoodsEntity>>());

        toSubscribe(observable, subscriber);
    }
}
