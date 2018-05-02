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


/**
 * Created by Administrator on 2017/7/21.
 */

public class NetworkUtil {
    public static final String URL_BASE = "http://39.108.51.161:8080/MobileShop/";




    public static String getDataByURL(String urlStr) throws Exception{
        //建立连接
        URL url = new URL(urlStr);
        //发起请求
        URLConnection conn = url.openConnection();
        //获取服务端返回的流
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //用来连接读取的每一行数据
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

