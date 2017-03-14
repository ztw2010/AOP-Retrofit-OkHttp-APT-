package com.oschina.learn.utils;

import android.view.View;
import android.widget.LinearLayout;

import com.apt.InstanceFactory;
import com.oschina.learn.App;
import com.oschina.learn.base.BaseViewHolder;
import com.oschina.learn.lib.aspect.MemoryCache;

import java.lang.reflect.ParameterizedType;

/**
 * Created by ztw on 2017/2/28.
 */

public class InstanceUtil {

    private static View PUPPET_VIEW = new LinearLayout(App.getAppContext());// 傀儡view

    /**
     * 通过实例工厂去实例化相应类
     *
     * @param <T> 返回实例的泛型类型
     * @return
     */
    public static <T> T getInstance(Class clazz) {
        try {
            return (T) InstanceFactory.create(clazz, BaseViewHolder.class.isAssignableFrom(clazz) ? PUPPET_VIEW : null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过实例工厂去实例化相应类
     *
     * @param <T> 返回实例的泛型类型
     * @return
     */
    public static <T> T getViewHolder(Class clazz, View view) {
        return (T) InstanceFactory.createVH(clazz, view);
    }

    public static <T> T getRepositoryInstance(Class cla) {
        try {
            if (cla.getGenericSuperclass() instanceof ParameterizedType)
                return (T) InstanceFactory.create((Class) ((ParameterizedType) (cla
                        .getGenericSuperclass())).getActualTypeArguments()[0], null);
            else return (T) InstanceFactory.create(cla, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @MemoryCache
    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
