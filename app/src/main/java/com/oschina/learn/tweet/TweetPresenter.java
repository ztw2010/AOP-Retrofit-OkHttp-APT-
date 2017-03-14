package com.oschina.learn.tweet;

import com.oschina.learn.lib.apt.InstanceFactory;

/**
 * Created by ztw on 2017/3/10.
 */
@InstanceFactory
public class TweetPresenter extends TweetContract.Presenter {

    @Override
    public void getTabList() {
        String[] mTabs = {"最新动态", "热门动态", "我的动弹"};
        mView.showTabList(mTabs);
    }

    @Override
    public void onAttached() {
        getTabList();
    }
}
