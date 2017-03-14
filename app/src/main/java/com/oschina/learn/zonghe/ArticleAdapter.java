package com.oschina.learn.zonghe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.oschina.learn.R;
import com.oschina.learn.utils.DensityUtil;
import com.oschina.learn.zonghe.bean.NewslistBean;

import java.util.List;

/**
 * Created by ztw on 2017/3/9.
 */

public class ArticleAdapter extends BaseRecyclerAdapter<ArticleAdapter.SimpleAdapterViewHolder>{

    private List<NewslistBean> newslistBeen;

    private int largeCardHeight, smallCardHeight;

    public ArticleAdapter(List<NewslistBean> newslistBeen, Context context) {
        this.newslistBeen = newslistBeen;
        largeCardHeight = DensityUtil.dip2px(context, 150);
        smallCardHeight = DensityUtil.dip2px(context, 100);
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_article, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        NewslistBean newslistBean = newslistBeen.get(position);
        holder.titleTv.setText(newslistBean.getTitle());
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            holder.rootView.getLayoutParams().height = position % 2 != 0 ? largeCardHeight : smallCardHeight;
        }
    }

    @Override
    public int getAdapterItemViewType(int position) {
        return 0;
    }

    public void setData(List<NewslistBean> newslistBeen) {
        this.newslistBeen = newslistBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getAdapterItemCount() {
        return newslistBeen.size();
    }

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTv;

        public View rootView;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                titleTv = (TextView) itemView.findViewById(R.id.tv_title);
                rootView = itemView.findViewById(R.id.card_view);
            }
        }
    }
}
