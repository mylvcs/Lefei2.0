package com.wangmengyun.lefei2.data;

import android.provider.BaseColumns;

/**
 * Created by wangmengyun on 2018/2/28.
 */

public final class UserContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private UserContract() {
    }

    public static final class UserEntry implements BaseColumns {

        /**
         * Name of database table for user
         */
        public final static String TABLE_NAME = "Users";

        /**
         * Unique ID number for the user.
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the user.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_USERNAME = "Username";

        public final static String COLUMN_USERADDRESS = "UserAddress";

        public final static String  COLUMN_USERTEL = "UserTelephone";

        public final static String  COLUMN_USERVISACARD= "UserVisaCardNumber";

        public final static String  COLUMN_USERFLIGHTNUMBER ="UserFlightnumber";


        /**
         * Gender of the User.
         * <p>
         * The only possible values are {@link #GENDER_MALE} or {@link #GENDER_FEMALE}
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_USER_GENDER = "gender";


        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

    }
//
//    该表格可能包含其他客户信息，例如姓名、地址、电话号码、飞行常客号码、信用卡信息、航班号码、座位号码等。
//
//    此外，航空公司需要表格来存储关于每日航班的信息。这些表格可能包含以下数据：飞机号、起飞机场、抵达机场、已售座位数、起飞时间、抵达时间、中转航班等。
//
}

