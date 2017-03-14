package com.oschina.learn.main;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.oschina.learn.utils.BaseUtils;
import com.oschina.learn.utils.ImageUtil;

/**
 * Created by ztw on 2017/3/14.
 */

public class ImageAnimator {
    String[] mImages = {
            "http://img002.21cnimg.com/photos/album/20160326/m600/B920004B5414AE4C7D6F2BAB2966491E.jpeg",
            "http://c.hiphotos.baidu.com/lvpics/h=800/sign=f08ecc016c63f624035d3403b745eb32/a2cc7cd98d1001e93b905337bf0e7bec54e7975d.jpg",
            "http://h.hiphotos.baidu.com/lvpics/h=800/sign=5c5e6d075dafa40f23c6c3dd9b65038c/03087bf40ad162d9dd7db82916dfa9ec8a13cd70.jpg",
            "http://g.hiphotos.baidu.com/zhidao/pic/item/c995d143ad4bd113bd6a9f2f58afa40f4bfb0503.jpg",
            "http://a.hiphotos.baidu.com/lvpics/h=800/sign=5f4d89167f1ed21b66c923e59d6cddae/4b90f603738da977e1cb9ecfb251f8198718e36a.jpg",
            "http://imgsrc.baidu.com/forum/pic/item/0b46f21fbe096b6395b188ff0c338744eaf8acf1.jpg",
            "http://g.hiphotos.baidu.com/lvpics/h=800/sign=98280c0dfb1f4134ff37087e151e95c1/9f510fb30f2442a7e7056c4bd743ad4bd01302d0.jpg",
            "http://f.hiphotos.baidu.com/lvpics/h=800/sign=a00362caa086c91717035f39f93c70c6/ac4bd11373f082026f66170d4cfbfbedab641b0f.jpg",
            "http://a.hiphotos.baidu.com/lvpics/w=1000/sign=e989094dcb11728b302d8822f8ccc1ce/72f082025aafa40f73724801ad64034f79f019b1.jpg",
            "http://e.hiphotos.baidu.com/lvpics/h=800/sign=07a3fdab6d600c33ef79d3c82a4d5134/8c1001e93901213fd4ce706352e736d12e2e958b.jpg"};

    int[] mColors = {
            Color.parseColor("#F44336"),
            Color.parseColor("#E91E63"),
            Color.parseColor("#9C27B0"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#03A9F4"),
            Color.parseColor("#00BCD4"),
            Color.parseColor("#009688"),
            Color.parseColor("#4CAF50"),
    };

    private static final float FACTOR = 0.1f;

    private final ImageView mTargetImage; // 原始图片
    private final ImageView mOutgoingImage; // 渐变图片

    private int mActualStart; // 实际起始位置

    private int mStart;
    private int mEnd;

    private boolean isSkip = false;//是否跳页
    CollapsingToolbarLayout collapsingToolbar;

    public ImageAnimator(CollapsingToolbarLayout collapsingToolbar, ImageView targetImage, ImageView outgoingImage) {
        this.collapsingToolbar = collapsingToolbar;
        mTargetImage = targetImage;
        mOutgoingImage = outgoingImage;
        ImageUtil.loadImg(mTargetImage, mImages[0]);
        collapsingToolbar.setContentScrimColor(mColors[0]);
        collapsingToolbar.setStatusBarScrimColor(mColors[0]);
    }

    /**
     * 启动动画, 之后选择向前或向后滑动
     *
     * @param startPosition 起始位置
     * @param endPosition   终止位置
     */
    public void start(int startPosition, int endPosition) {
        if (Math.abs(endPosition - startPosition) > 1) {
            isSkip = true;
        }
        mActualStart = startPosition;
        Log.e("DEBUG", "startPosition: " + startPosition + ", endPosition: " + endPosition);
        // 终止位置的图片

        //@DrawableRes int incomeId = ids[endPosition % ids.length];

        // 原始图片
        mOutgoingImage.setImageDrawable(mTargetImage.getDrawable()); // 原始的图片

        // 起始图片
        mOutgoingImage.setTranslationX(0f);

        mOutgoingImage.setVisibility(View.VISIBLE);
        mOutgoingImage.setAlpha(1.0f);

        // 目标图片
        //   mTargetImage.setImageResource(incomeId);
        ImageUtil.loadImg(mTargetImage, mImages[endPosition]);
        mStart = Math.min(startPosition, endPosition);
        mEnd = Math.max(startPosition, endPosition);
    }

    /**
     * 滑动结束的动画效果
     *
     * @param endPosition 滑动位置
     */
    public void end(int endPosition) {
        isSkip = false;
        //@DrawableRes int incomeId = ids[endPosition % ids.length];
        Log.e("DEBUG", "endPosition: " + endPosition);
        mTargetImage.setTranslationX(0f);

        // 设置原始图片
        if (endPosition == mActualStart) {
            mTargetImage.setImageDrawable(mOutgoingImage.getDrawable());
        } else {
            ImageUtil.loadImg(mTargetImage, mImages[endPosition]);
            collapsingToolbar.setContentScrimColor(mColors[endPosition]);
            collapsingToolbar.setStatusBarScrimColor(mColors[endPosition]);
            //mTargetImage.setImageResource(incomeId);
            mTargetImage.setAlpha(1f);
            mOutgoingImage.setVisibility(View.GONE);
        }
    }

    // 向前滚动, 比如0->1, offset滚动的距离(0->1), 目标渐渐淡出
    public void forward(int position, float positionOffset) {
        if (isSkip) return;
        // Log.e("DEBUG-WCL", "forward-positionOffset: " + positionOffset);
        int width = mTargetImage.getWidth();
        mOutgoingImage.setTranslationX(-positionOffset * (FACTOR * width));
        mTargetImage.setTranslationX((1 - positionOffset) * (FACTOR * width));
        int color = BaseUtils.evaluate(positionOffset, mColors[position], mColors[position + 1]);
        collapsingToolbar.setContentScrimColor(color);
        collapsingToolbar.setStatusBarScrimColor(color);
        mTargetImage.setAlpha(positionOffset);
    }

    // 向后滚动, 比如1->0, offset滚动的距离(1->0), 目标渐渐淡入
    public void backwards(int position, float positionOffset) {
        if (isSkip) return;
        // Log.e("DEBUG-WCL", "backwards-positionOffset: " + positionOffset);
        int width = mTargetImage.getWidth();
        mOutgoingImage.setTranslationX((1 - positionOffset) * (FACTOR * width));
        mTargetImage.setTranslationX(-(positionOffset) * (FACTOR * width));

        int color = BaseUtils.evaluate(1 - positionOffset, mColors[position + 1], mColors[position]);
        collapsingToolbar.setContentScrimColor(color);
        collapsingToolbar.setStatusBarScrimColor(color);
        mTargetImage.setAlpha(1 - positionOffset);
    }

    // 判断停止
    public boolean isWithin(int position) {
        return position >= mStart && position < mEnd;
    }
}
