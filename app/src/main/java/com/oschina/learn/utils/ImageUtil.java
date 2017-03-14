package com.oschina.learn.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by zhongruan on 2017/3/14.
 */

public class ImageUtil {
    public static void loadImg(ImageView v, String url) {
        Glide.with(v.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(v);
    }
}
