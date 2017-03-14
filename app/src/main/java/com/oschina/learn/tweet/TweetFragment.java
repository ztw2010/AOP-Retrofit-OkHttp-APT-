package com.oschina.learn.tweet;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.oschina.learn.R;
import com.oschina.learn.base.BaseFragment;
import com.oschina.learn.widget.TitleBar;

import butterknife.Bind;

/**
 * Created by ztw on 2017/3/1.
 */

public class TweetFragment extends BaseFragment<TweetPresenter> implements TweetContract.View{

    @Bind(R.id.nav_title_bar)
    TitleBar titleBar;

    @Bind(R.id.tab_nav)
    TabLayout mTabNav;

    @Bind(R.id.base_viewPager)
    ViewPager mBaseViewPager;

    private AppCompatActivity appCompatActivity;

    public static TweetFragment getInstance() {
        TweetFragment tweetFragment = new TweetFragment();
        tweetFragment.tag = "TWEETFRAGMENT";
        return tweetFragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        titleBar.setTitle(R.string.main_tab_name_news);
        titleBar.setIcon(R.mipmap.btn_search_normal);
        titleBar.setIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击图标", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        appCompatActivity = (AppCompatActivity) getActivity();
        return R.layout.fragment_tweet;
    }

    @Override
    public void showTabList(final String[] mTabs) {
        mBaseViewPager.setCurrentItem(0, true);
    }
}
