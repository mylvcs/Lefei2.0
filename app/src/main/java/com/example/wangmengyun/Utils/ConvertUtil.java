package com.example.wangmengyun.Utils;

import com.example.wangmengyun.data.Order;

import org.bson.Document;

import java.lang.reflect.Field;

/**
 * Created by wangmengyun on 2018/4/29.
 * NoSQL书上的
 */
public class ConvertUtil {

    public static Document convertDoc(Order o)
                throws IllegalArgumentException, IllegalAccessException {
            Document document = new Document();
            Field[] fields = o.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                document.append(field.getName(), field.get(o));
            }
            return document;
        }

    }
