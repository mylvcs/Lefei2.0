package com.example.wangmengyun.Utils;

/**
 * Created by wangmengyun on 2018/5/2.
 */

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class NetworkUtil {

    public static final String URL_BASE = "www.wmynuaa.com:3000/";
    public static final String QUERY_STU_URL = URL_BASE + "QueryAllStuServlet";
    public static final String INSERT_STU_URL = URL_BASE  + "AddStuServlet";
    public static final String REGISTER_TELEPHONE_URL =URL_BASE + "RegisterServlet";
    public static final String STUDENT_INPUT_URL =URL_BASE + "FinshRegisterServlet";
    public static final String QUERY_ALL_JOB_URL =URL_BASE + "QueryAllTimeJobServlet";
    public static final String QUERY_ALL_GAME_URL=URL_BASE+"QueryAllAcrivitiesServlet";
    public static final String GUESS_YOU_URL=URL_BASE+"RecomServlet";
    public static final String POPULAR_TASTE_URL=URL_BASE+"HotSaleServlet";
    public static final String TODAY_SALE_URL=URL_BASE+"GetTodayDiscountServlet";
    public static final String SHOPPING_CART_URL=URL_BASE+"HasAddShoppingCar";
    public static final String HISTORY_ORDER_URL=URL_BASE+"HadCustomServlet";
    public static final String MY_LOOKFOR_HISTORY_URL=URL_BASE+"QuerySkimservlet";
    public static final String MY_COLLECTION_URL=URL_BASE+"QueryCollectFood";
    public static final String MER_SEE_ORDER_URL=URL_BASE+"MerSeeOrderServlet";
    public static final String UP_LOAD_FOOD_URL=URL_BASE+"AddFoodServlet";
    public static final String PUSH_PATTIME_URL=URL_BASE+"MerPushPartimeServlet";
    public static final String PUSH_ACTIVITY_URL=URL_BASE+"MerPushActivityServlet";
    public static final String MER_SEE_SALE_URL=URL_BASE+"MerSeeSaleServlet";
    public static final String RECHARGE_MONEY  =URL_BASE + "RechangeServlet";
    public static final String QUERY_MONEY  =URL_BASE + "QueryUserByIdServlet";
    public static final String SAVE_PERSONAL_SETTING = URL_BASE +"SavePersonalSetting";
    public static final String CHANGE_LOGIN_PSD = URL_BASE +"UpdatePersonInfoServlet";
    public static final String QUERY_CANTEEN = URL_BASE +"QueryCanteen";
    public static final String MY_HEALTH_URL=URL_BASE+"QueryRecentCalorie"; //*
    public static  final String POPULAR_SHARE_URL=URL_BASE+"PopularShareServlet";//*
    public static  final String TODAY_SHARE_URL=URL_BASE+"TodayShareServlet"; //*
    public static  final String USER_LOGIN_URL=URL_BASE+"LoginServlet";  //*
    public static  final String MANAGER_LOGIN_URL=URL_BASE+"ManagerLoginServlet"; //*
    public static final String  MER_SEE_COMPLAIN=URL_BASE+"ManagerSeeComplainServlet"; //*
    public static final  String MY_COMPLAIN=URL_BASE+"UploadComplainServlet";
    public static final String QUERY_CANTEEN_URL = URL_BASE + "QueryAllResServlet";
    public static final String QUERY_FOODNAME_URL = URL_BASE + "QueryAllFoodNameServlet";
    public static final String QUERY_AllFOOD_URL = URL_BASE + "QueryAllFoodrServlet";
    public static final String QUERY_AllCOMMENT_URL = URL_BASE + "GetAllComServlet";
    public static final String QUERY_USERBYID_URL = URL_BASE + "QueryUserByIdServlet";
    public static final String ADDFOODTOCART = URL_BASE +"AddShopingCar";
    public static final String ADDFOODTOCOLLECTION = URL_BASE +"AddCollectionServlet";
    public static final String QUERYPAYPASSWORD = URL_BASE +"QuerypaypasswordServlet";
    public static final String CART_PAY_URL = URL_BASE +"CheckoutServlet";
    public static final  String MY_COMMENT=URL_BASE+"ComFoodServlet";
    public static final String ADDCOLLECT = URL_BASE +"CollectServlet";
    public static final String UPDATE_SHOPPING_CART_URL = URL_BASE +"UpdateShoppingCarServlet";
    public static final String GETFETCHNUM = URL_BASE +"CheckoutServlet";
    public static final String UPLOADOR_USERNAME = URL_BASE +"LoadIconUserName";
    public static  final String SET_PICTURE=URL_BASE+"UpdateIcon";

    public static String getDataByURL(String urlStr) throws Exception{
        //建立连接
        URL url = new URL(urlStr);
        //
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuffer sb = new StringBuffer();
        //用来读取每一行数据
        String line = null;
        while((line=br.readLine())!=null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public static String postDataByURL(String urlStr, Map<String, String> map) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(urlStr);

        List<NameValuePair> list = new ArrayList<>();
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
            list.add(pair);
        }

        post.setEntity(new UrlEncodedFormEntity(list));
        HttpResponse response = client.execute(post);

        return EntityUtils.toString(response.getEntity());
    }

    public static JSONObject getJSONByURL(String urlStr) throws  Exception{
        return new JSONObject(getDataByURL(urlStr));
    }

    public static JSONArray getJSONArrayByURL(String urlStr) throws  Exception{
        return new JSONArray(getDataByURL(urlStr));
    }
}

