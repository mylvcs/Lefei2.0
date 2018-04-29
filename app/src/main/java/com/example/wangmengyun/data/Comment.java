package com.example.wangmengyun.data;

import java.util.Date;

/**
 * Created by wangmengyun on 2018/4/29.
 *
 * 评论
 */

public class Comment  {
    private String userName;

    private String content;
    private int star;
    private Date createdTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
