package com.example.wangmengyun.activity;

import java.io.IOException;
import java.util.Date;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.wangmengyun.Utils.ConvertUtil;
import com.example.wangmengyun.Utils.MongoDBUtil;
import com.example.wangmengyun.data.Goods;
import com.example.wangmengyun.data.Order;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;

public class MongoDBTest {

    private MongoClient mongoClient;

    @Before
    // 注解before 表示在方法前执行
    public void initMongoClient() throws IOException {
        mongoClient = MongoDBUtil.initMongo();
    }

    @Test(timeout = 1000)
    // timeout表示该测试方法执行超过1s会抛出异常
    public void saveOrderTest() throws IllegalArgumentException,
            IllegalAccessException {

        Document document = ConvertUtil.convertDoc(this.initOrder());

        mongoClient.getDatabase("OrderTest")
                .getCollection("Order").insertOne(document);
    }

    @Test
    public void queryOrderTest() {
        FindIterable<Document> iter = mongoClient
                .getDatabase("OrderTest").getCollection("Order")
                .find(new Document("price", "$sum"));
        System.out.println(iter.first().toJson());
    }

    @After
    public void closeMongoClient() {
        mongoClient.close();
    }

    private Order initOrder() {
        Goods goods = new Goods();

        goods.setGoodsInfo("商品名称：华硕FX53VD商品编号：4380878商品毛重：4.19kg商品产地：中国大陆");
        goods.setId(4380878);

        goods.setPrice(10);
        Order historyOrder = new Order();
        historyOrder.setCreatedDate(new Date());
  //      historyOrder.setGoodsList(JsonUtil.toJson(goods));
        historyOrder.setOrderId(3456712);
        historyOrder.setUserName("zhangsan");
        return historyOrder;
    }

}
