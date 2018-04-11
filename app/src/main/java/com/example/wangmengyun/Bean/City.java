package com.example.wangmengyun.Bean;

import java.util.UUID;

/**
 *City 类有CityName
 */

//public class City {
//    private UUID mId;
//
//    private String name;
//
//    public City(UUID mId, String name) {
//        super();
//        mId = UUID.randomUUID();
//        this.name = name;
//
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//}

public class City {
    private String name;
    private String pinyin;

    public City() {}

    public City(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}


