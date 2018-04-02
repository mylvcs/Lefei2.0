package com.example.wangmengyun.database;

import java.util.Date;

/**
 * Created by wangmengyun on 04/02/2018
 * 定义数据表字段
 *
 */

public class FlightDbSchema {
    public static final class FlightTable {
        public static final String NAME = "flights";

        public static final class Cols {
            public static final String UUID = "uuid";

            public static final String DEPARTCITY = "departure_city";
            public static final String ARRIVECITY = "arrive_city";
            public static final String PRICE = "price";
        }
    }
}
