package com.app.lib.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.app.lib.widget.interfaces.IAdapter;
import com.app.lib.widget.interfaces.IData;

import java.util.ArrayList;
import java.util.List;

import static com.app.lib.widget.BaseAdapterHelper.get;

/**
 * 类 描 述: 通用Adapter,适用于ListView/GridView,简化大量重复代码
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public abstract class CommonAdapter<T> extends BaseAdapter implements IData<T>, IAdapter<T> {
    private final Context mContext;
    private final int     mLayoutResId;
    private final List<T> mData;

    public CommonAdapter(@NonNull Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    public CommonAdapter(@NonNull Context context, int layoutResId, List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        this.mContext = context;
        this.mLayoutResId = layoutResId;
    }

    @Override public List<T> getData() {
        return mData;
    }

    @Override public int getCount() {
        return mData.size();
    }

    @Override public T getItem(int position) {
        return position >= mData.size() ? null : mData.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public int getLayoutResId(T item, int position) {
        return this.mLayoutResId;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        final T item = getItem(position);
        final BaseAdapterHelper helper = get(mContext, convertView, parent,
                getLayoutResId(item, position), position);
        onUpdate(helper, item, position);
        helper.setAssociatedObject(item);
        return helper.getView();
    }

    @Override public boolean isEnabled(int position) {
        return position < mData.size();
    }

    @Override public void add(@NonNull T item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    @Override public void addAll(@NonNull List<T> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override public void set(@NonNull T oldItem, @NonNull T newItem) {
        set(mData.indexOf(oldItem), newItem);
    }

    @Override public void set(int index, @NonNull T item) {
        if (index >= 0 && index < getCount()) {
            mData.set(index, item);
            notifyDataSetChanged();
        }
    }

    @Override public void remove(@NonNull T item) {
        mData.remove(item);
        notifyDataSetChanged();
    }

    @Override public void remove(int index) {
        if (index >= 0 && index < getCount()) {
            mData.remove(index);
            notifyDataSetChanged();
        }
    }

    @Override public void replaceAll(@NonNull List<T> item) {
        mData.clear();
        mData.addAll(item);
        notifyDataSetChanged();
    }

    @Override public boolean contains(@NonNull T item) {
        return mData.contains(item);
    }

    @Override public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}
