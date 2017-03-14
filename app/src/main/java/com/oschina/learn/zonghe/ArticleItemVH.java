package com.oschina.learn.zonghe;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.oschina.learn.App;
import com.oschina.learn.R;
import com.oschina.learn.articledetail.ArticleDetailActivityDispatcher;
import com.oschina.learn.base.BaseViewHolder;
import com.oschina.learn.lib.apt.InstanceFactory;
import com.oschina.learn.utils.StringUtils;
import com.oschina.learn.zonghe.bean.NewslistBean;

import java.util.Date;

import butterknife.Bind;

/**
 * Created by ztw on 2017/3/13.
 */
@InstanceFactory(InstanceFactory.typeVH)
public class ArticleItemVH extends BaseViewHolder<NewslistBean> implements View.OnClickListener{

    @Bind(R.id.tv_title)
    public TextView tvTitle;

    @Bind(R.id.tv_time)
    public TextView pubTimeTv;

    @Bind(R.id.tv_info_comment)
    public TextView commentTv;

    private String currentDateStr;

    public ArticleItemVH(View v) {
        super(v);
        currentDateStr = StringUtils.YYYYMMDDHHMMSS.get().format(new Date());
    }

    @Override
    public void onBindViewHolder(View view, NewslistBean newslistBean) {
        super.onBindViewHolder(view, newslistBean);
        //view.setOnClickListener(this);
        String authorName = newslistBean.getAuthor();
        String pubDateStr = StringUtils.getDateString(newslistBean.getPubDate());
        if(!TextUtils.isEmpty(authorName)){
            authorName = authorName.trim();
            pubTimeTv.setText(String.format("@%s %s",
                    (authorName.length() > 9 ? authorName.substring(0, 9) : authorName),
                    StringUtils.formatSomeAgo(pubDateStr)));
        }else{
            pubTimeTv.setText(StringUtils.formatSomeAgo(pubDateStr));
        }
        commentTv.setText(String.valueOf(newslistBean.getCommentCount()));
        String title = newslistBean.getTitle();
        if (StringUtils.isSameDay(currentDateStr, pubDateStr)){
            String text = "[icon] " + title;
            Drawable drawable = ContextCompat.getDrawable(App.getAppContext().getApplicationContext(), R.mipmap.ic_label_today);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
            SpannableString spannable = new SpannableString(text);
            spannable.setSpan(imageSpan, 0, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            tvTitle.setText(spannable);
            tvTitle.setTextSize(16.0f);
        }else{
            tvTitle.setText(title);
        }
    }

    @Override
    public void onClick(View v) {
        new ArticleDetailActivityDispatcher(data.getId()).start(App.getAppContext().getCurActivity());
    }

    @Override
    public int getType() {
        return R.layout.item_article;
    }
}
