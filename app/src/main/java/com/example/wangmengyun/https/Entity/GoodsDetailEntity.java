package com.example.wangmengyun.https.Entity;

import java.util.UUID;

/**
 * Created by wangmengyun on 2018/5/5.
 */

public class GoodsDetailEntity {

    private UUID mId;

    private String name;


    private String price;

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
