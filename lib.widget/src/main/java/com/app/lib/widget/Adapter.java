package com.app.lib.widget;

import android.support.annotation.NonNull;

import com.app.lib.widget.interfaces.ImageLoad;


/**
 * 类 描 述: Adapter全局配置
 */
@SuppressWarnings({ "WeakerAccess", "unused" })
public final class Adapter {
    static volatile Adapter singleton = null;

    private ImageLoad mImageLoadImpl;

    private Adapter(Builder builder) {
        mImageLoadImpl = builder.mImageLoadImpl;
    }

    public ImageLoad getImageLoad() {
        return mImageLoadImpl;
    }

    public static Adapter config(@NonNull Builder builder) {
        if (singleton == null) {
            synchronized (Adapter.class) {
                if (singleton == null) {
                    singleton = builder.build();
                }
            }
        }
        return singleton;
    }

    public static final class Builder {
        private ImageLoad mImageLoadImpl;

        public Builder() { }

        public Builder setImageLoad(ImageLoad imageLoad) {
            mImageLoadImpl = imageLoad;
            return this;
        }

        public Adapter build() {
            return new Adapter(this);
        }
    }
}
