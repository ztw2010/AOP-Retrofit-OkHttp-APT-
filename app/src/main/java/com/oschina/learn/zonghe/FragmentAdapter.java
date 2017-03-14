package com.oschina.learn.zonghe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.oschina.learn.base.BaseListFragment;

import java.util.List;

/**
 * Created by ztw on 2017/3/9.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<BaseListFragment> mFragments;

    private String[] mTitles;

    public static FragmentAdapter newInstance(FragmentManager fm, List<BaseListFragment> fragments, String[] titles) {
        FragmentAdapter mFragmentAdapter = new FragmentAdapter(fm);
        mFragmentAdapter.mFragments = fragments;
        mFragmentAdapter.mTitles = titles;
        return mFragmentAdapter;
    }

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "未知";
        switch (position){
            case 0:
                title = "所有";
                break;
            case 1:
                title = "综合新闻";
                break;
            case 2:
                title = "软件更新";
                break;
        }
        return title;
    }
}
