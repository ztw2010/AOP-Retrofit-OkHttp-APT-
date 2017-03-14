package com.oschina.learn.bean;

import java.util.Map;

import rx.Observable;

/**
 * Created by ztw on 16/7/19.
 * 仓库类，定义仓库货物(数据)，钥匙，来源
 */
public abstract class Repository<T> {

    public T data;//货物

    public Map<String, Object> param;//钥匙

    public abstract Observable<DataArr<T>> getPageAt(int page);//来源

    public abstract Observable<T> getPageAt2(int page);//来源
}
