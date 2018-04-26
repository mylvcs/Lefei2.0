package com.example.wangmengyun.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wangmengyun on 2018/4/26.
 */
@Entity
public class Category implements Serializable{

    @Id
    private Long flight_id;

    private String name;
    private String path;

    private int sort;
    private String image;
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getSort() {
        return this.sort;
    }
    public void setSort(int sort) {
        this.sort = sort;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getFlight_id() {
        return this.flight_id;
    }
    public void setFlight_id(Long flight_id) {
        this.flight_id = flight_id;
    }
    @Generated(hash = 220618158)
    public Category(Long flight_id, String name, String path, int sort, String image) {
        this.flight_id = flight_id;
        this.name = name;
        this.path = path;
        this.sort = sort;
        this.image = image;
    }
    @Generated(hash = 1150634039)
    public Category() {
    }


}
