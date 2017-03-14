package com.app.lib.widget.interfaces;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * 类 描 述: 数据操作接口规范
 */
@SuppressWarnings("unused")
public interface IData<T> {

    /**
     * 获取当前数据列表
     * @return
     */
    List<T> getData();

    /**
     * 添加一条数据
     * @param item
     */
    void add(@NonNull T item);

    /**
     * 添加多条数据
     * @param list
     */
    void addAll(@NonNull List<T> list);

    /**
     * 更新一条数据
     * @param oldItem
     * @param newItem
     */
    void set(@NonNull T oldItem, @NonNull T newItem);

    /**
     * 根据下标更新一条数据
     * @param index
     * @param item
     */
    void set(int index, @NonNull T item);

    /**
     * 删除一条数据
     * @param item
     */
    void remove(@NonNull T item);

    /**
     * 根据下标删除一条数据
     * @param index
     */
    void remove(int index);

    /**
     * 替换所有数据
     * @param item
     */
    void replaceAll(@NonNull List<T> item);

    /**
     * 是否存在某个对象
     * @param item
     * @return
     */
    boolean contains(@NonNull T item);

    /**
     * 清空数据
     */
    void clear();
}
