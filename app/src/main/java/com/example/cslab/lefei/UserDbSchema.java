package com.example.cslab.lefei;

/**
 * Created by CSLab on 2018/1/15.
 */

public class UserDbSchema {

    public static final class UserTable{
        public static final String NAME= "USERS";
        public static final class Cols{
            public static final String UserID= "userId";
            public static final String UserName= "username";
            public static final String UserPassword= "pwd";
            public static final String UserTicket = "ticket";

        }
    }
}
