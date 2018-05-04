package com.example.wangmengyun.data;


/**
 * Created by wangmengyun on 2018/4/29.
 */

public class User {
    //APP用户字段
    private Id id;

    private String user;

    public Id get_id() {
        return id;
    }

    public void set_id(Id _id) {
        this.id = _id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String username;

    private String Tel;
    private String email;

    private String jifen; //积分

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    private String GoogleAccount;

    private String FBaccount;

    public String getGoogleAccount() {
        return GoogleAccount;
    }

    public void setGoogleAccount(String googleAccount) {
        GoogleAccount = googleAccount;
    }

    public String getFBaccount() {
        return FBaccount;
    }

    public void setFBaccount(String FBaccount) {
        this.FBaccount = FBaccount;
    }
}
