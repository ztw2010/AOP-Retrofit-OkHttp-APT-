package com.app.lib.widget.interfaces;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

/**
 * 类 描 述: 网络图片加载接口规范
 */
public interface ImageLoad {

    void load(@NonNull Context context, @NonNull ImageView imageView, @NonNull String imageUrl);
}
