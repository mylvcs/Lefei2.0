package com.example.wangmengyun.Bean;

/**
 * Created by wangmengyun on 2018/4/19.
 */

public class CityPair {
    private String chufaCity;
    private String daodaCity;

    public CityPair() {
    }

    public CityPair(String chufa, String daoda) {
        this.chufaCity = chufa;
        this.daodaCity = daoda;
    }

    public String getChufaCity() {
        return chufaCity;
    }

    public void setChufaCity(String chufaCity) {
        this.chufaCity = chufaCity;
    }

    public String getDaodaCity() {
        return daodaCity;
    }

    public void setDaodaCity(String daodaCity) {
        this.daodaCity = daodaCity;
    }
}