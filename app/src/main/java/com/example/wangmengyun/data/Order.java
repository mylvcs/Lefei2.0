package com.example.wangmengyun.data;

import java.util.Date;

/**
 * Order即订单，有字段用户名，航班号，订单号，创建时间。
 */

public class Order {

    private long orderId;
    private Date createdDate;
    private String userName;

    private String FlightNumber;
    private String goodsList;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createDate) {
        this.createdDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        FlightNumber = flightNumber;
    }
}
