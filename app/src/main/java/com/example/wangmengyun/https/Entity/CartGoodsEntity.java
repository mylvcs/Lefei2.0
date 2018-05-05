package com.example.wangmengyun.https.Entity;

/**
 * Created by wangmengyun on 2018/5/5.
 */

public class CartGoodsEntity {

    private int Cart_id;
    private int Goods_num;
    private String name;
    private String Amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public int getCart_id() {
        return Cart_id;
    }

    public void setCart_id(int cart_id) {
        Cart_id = cart_id;
    }

    public int getGoods_num() {
        return Goods_num;
    }

    public void setGoods_num(int goods_num) {
        Goods_num = goods_num;
    }
}
