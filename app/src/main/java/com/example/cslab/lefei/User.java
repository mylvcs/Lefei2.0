package com.example.cslab.lefei;

import java.util.UUID;

/**
 * Created by CSLab on 2018/1/15.
 */

public class User {
    private UUID UserId;
    private String UserName;
    private String UserPassword;

    public User(){
        UserId= UUID.randomUUID();
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public UUID getUserId() {
        return UserId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }
}
