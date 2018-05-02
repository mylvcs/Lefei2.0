package com.example.wangmengyun.https.Entity;

/**
 * Created by wangmengyun on 2018/5/1.
 */

public class MemberEntity {
    private int member_id;
    private String password;

    private String uname;
    private String email;

    private String mobile;
    private String image;

    private Object regtime;

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getRegtime() {
        return regtime;
    }

    public void setRegtime(Object regtime) {
        this.regtime = regtime;
    }
}
