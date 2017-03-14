package com.oschina.learn.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ztw on 2017/3/1.
 * Java对象和JSON字符串相互转化工具类
 */

public class JsonUtil<T> {

    public List<T> json2List(String jsonStr, String className) {
        List<T> list = new ArrayList<T>();
        if (jsonStr != null && !"".equals(jsonStr)) {
            try {
                JSONArray jarry = new JSONArray(jsonStr);
                int length = jarry.length();
                for (int i = 0; i < length; i++) {
                    list.add(json2Bean(jarry.getString(i), className));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public T json2Bean(String jsonString, String className) {
        try {
            Class<?> clazz = Class.forName(className);
            if (TextUtils.isEmpty(jsonString)) {
                return (T) clazz.newInstance();
            } else {
                jsonString = jsonString.replaceAll(", null", "");
                GsonBuilder builder = new GsonBuilder();
//                builder.registerTypeAdapter(Uri.class, new UriDeserializer());
                builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
                Gson gson = builder.create();
                T t = (T) gson.fromJson(jsonString, clazz);
                return t;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String bean2Json(T t) {
        return new Gson().toJson(t);
    }

    public String list2Json(List<T> t) {
        return new Gson().toJson(t);
    }

}
