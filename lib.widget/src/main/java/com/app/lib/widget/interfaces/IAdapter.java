package com.app.lib.widget.interfaces;


import com.app.lib.widget.BaseAdapterHelper;

/**
 * 类 描 述: 扩展的Adapter接口规范
 */
public interface IAdapter<T> {

    /**
     * 数据更新回调
     * @param helper
     * @param item
     * @param position
     */
    void onUpdate(BaseAdapterHelper helper, T item, int position);

    /**
     * 当前Item的布局文件
     * @param item
     * @param position
     * @return
     */
    int getLayoutResId(T item, int position);
}
