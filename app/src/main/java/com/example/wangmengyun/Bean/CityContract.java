package com.example.wangmengyun.Bean;

import android.provider.BaseColumns;

/**
 * 城市数据库
 * 字段：城市ID，城市名，所在经度，纬度，所属国家名，机场数量。
 */

public class CityContract {
	
	public static final class CityEntry implements BaseColumns {
		public static final String TABLE_NAME = "City";
		public static final String COLUMN_CITY_NAME = "CityName";
		public static final String COLUMN_CITY_OF_COUNTRY = "CountryName";
		public static final String COLUMN_UUID = "CityID";
		public static final String COLUMN_JINGDU = "CityJingdu";
		public static final String COLUMN_WEIDU = "CityWeidu";
	}
}
