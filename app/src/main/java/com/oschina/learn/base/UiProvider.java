package com.oschina.learn.base;


import com.oschina.learn.lib.apt.InstanceFactory;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ztw on 2017/3/1.
 */

@InstanceFactory
public class UiProvider {

    private Map<Serializable, Object> registViewsMap = Collections.synchronizedMap(new HashMap<Serializable, Object>());
    /**
     * 注册UI组件.
     * 以类为KEY.
     * @param view UI组件.
     */
    public void regist(Object view) {
        if (view == null) {
            throw new RuntimeException("不能注删空对象");
        }
        registViewsMap.put(view.getClass(), view);
    }

    /**
     * 注销UI组件.
     * @param view UI组件.
     */
    public void unregist(Object view) {
        if (view == null) {
            return;
        }
        registViewsMap.remove(view.getClass());
    }

    /**
     * 以TAG为KEY，注册UI组件.
     * @param tag 组件TAG.
     * @param view UI组件.
     */
    public void regist(String tag, Object view) {
        if (tag == null) {
            throw new RuntimeException("不能注删空TAG");
        }
        if (view == null) {
            throw new RuntimeException("不能注删空对象");
        }
        registViewsMap.put(tag, view);

    }


    /**
     * 注销KEY为TAG的UI组件.
     * @param tag 组件TAG.
     */
    public void unregist(String tag) {
        if (tag == null) {
            return;
        }
        registViewsMap.remove(tag);
    }

    /**
     * 用于注销时操作.
     */
    public void clear() {
        registViewsMap.clear();
    }

    /**
     * 根据类获取UI组件,如果没有则抛出异常.
     * @param clazz UI组件的类.
     * @return UI组件.
     */
    public <T> T getUI(Class<T> clazz) {
        if (registViewsMap.containsKey(clazz)) {
            return (T) registViewsMap.get(clazz);
        }
        return null;
    }

    /**
     * 根据TAG获取UI组件,如果没有则抛出异常.
     * @param tag 组件TAG.
     * @param clazz UI组件的类.
     * @return UI组件.
     */
    public <T> T getUI(String tag, Class<T> clazz) {
        if (registViewsMap.containsKey(tag)) {
            return (T) registViewsMap.get(tag);
        }
        return null;
    }
}
