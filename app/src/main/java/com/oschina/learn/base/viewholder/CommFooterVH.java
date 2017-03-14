package com.oschina.learn.base.viewholder;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oschina.learn.R;
import com.oschina.learn.base.BaseViewHolder;
import com.oschina.learn.lib.apt.InstanceFactory;

import butterknife.Bind;

@InstanceFactory(InstanceFactory.typeVH)
public class CommFooterVH extends BaseViewHolder<Boolean> {
    @Bind(R.id.progressbar)
    public ProgressBar progressbar;
    @Bind(R.id.tv_state)
    public TextView tv_state;
    public static final int LAYOUT_TYPE = R.layout.list_footer_view;

    public CommFooterVH(View view) {
        super(view);
    }

    @Override
    public int getType() {
        return LAYOUT_TYPE;
    }

    @Override
    public void onBindViewHolder(View view, Boolean isHasMore) {
        progressbar.setVisibility(isHasMore ? View.VISIBLE : View.GONE);
        tv_state.setText(isHasMore ? "正在加载" : "已经到底");
    }
}