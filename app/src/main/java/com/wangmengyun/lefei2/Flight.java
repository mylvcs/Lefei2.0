package com.wangmengyun.lefei2;


import java.util.Date;
import java.util.UUID;

/**
 * Created by wangmengyun on 2018/2/28.
 */

//    此外，航空公司需要表格来存储关于每日航班的信息。这些表格可能包含以下数据：飞机号、起飞机场、抵达机场、已售座位数、起飞时间、抵达时间、中转航班等。


public class Flight {
    private UUID mFlightID;
    private String mAirline;
    private String mAirport_Depart;
    private String mAirport_Arrive;
    private int seatnumber;
    private int seat_saled;
    private int seat_tosale;
    private Date mDate;

    public Flight() {
        mFlightID = UUID.randomUUID();
        mDate = new Date();

    }

    public UUID getmFlightID() {
        return mFlightID;
    }

    public void setmFlightID(UUID mFlightID) {
        this.mFlightID = mFlightID;
    }

    public String getmAirline() {
        return mAirline;
    }

    public void setmAirline(String mAirline) {
        this.mAirline = mAirline;
    }

    public String getmAirport_Depart() {
        return mAirport_Depart;
    }

    public void setmAirport_Depart(String mAirport_Depart) {
        this.mAirport_Depart = mAirport_Depart;
    }

    public String getmAirport_Arrive() {
        return mAirport_Arrive;
    }

    public void setmAirport_Arrive(String mAirport_Arrive) {
        this.mAirport_Arrive = mAirport_Arrive;
    }

    public int getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(int seatnumber) {
        this.seatnumber = seatnumber;
    }

    public int getSeat_saled() {
        return seat_saled;
    }

    public void setSeat_saled(int seat_saled) {
        this.seat_saled = seat_saled;
    }

    public int getSeat_tosale() {
        return seat_tosale;
    }

    public void setSeat_tosale(int seat_tosale) {
        this.seat_tosale = seat_tosale;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
