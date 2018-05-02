package com.example.wangmengyun.https.service;

import com.example.wangmengyun.Bean.HttpResult;
import com.example.wangmengyun.https.Entity.FavoriteGoodsEntity;
import com.example.wangmengyun.https.Entity.GoodsEntity;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by wangmengyun on 2018/5/1.
 */

public interface GoodsService {

    @GET("goods/cat/{catId}")
    Observable<HttpResult<List<GoodsEntity>>> list(
            @Path("catId")int catId

    );

    //添加到购物车
    @POST("cart")
    Observable<HttpResult> addToCart(
            @Field("memberId") int memberId,
            @Field("goodsId") int goodsId

    );

    @POST("like")
    Observable<HttpResult> addToFavorite(
            @Field("memberId") int memberId,
            @Field("goodsId") int goodsId
    );


    @GET("like/member/{memberId")
    Observable<HttpResult<List<FavoriteGoodsEntity>>> favourieList(
            @Path("memberId") int memberId
    );
}
