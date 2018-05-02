package com.example.wangmengyun.https.service;

import com.example.wangmengyun.Bean.HttpResult;
import com.example.wangmengyun.database.Category;
import com.example.wangmengyun.https.Entity.CategoryEntity;

import java.util.List;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by wangmengyun on 2018/5/1.
 */

public interface CategoryService {

    @GET("cat/show")
    Observable<HttpResult<List<CategoryEntity>>> getTopList();

    @GET("cat/show/id")
    Observable<HttpResult<List<CategoryEntity>>> getSecondList(
            @Path("id") int id
    );
}
