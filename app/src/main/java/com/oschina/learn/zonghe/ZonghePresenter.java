package com.oschina.learn.zonghe;

import com.oschina.learn.lib.apt.InstanceFactory;

/**
 * Created by ztw on 2017/3/8.
 */
@InstanceFactory
public class ZonghePresenter extends ZongheContract.Presenter{

    @Override
    public void getTabList() {
        String[] mTabs = {"1", "2", "3"};
        mView.showTabList(mTabs);
    }

    @Override
    public void onAttached() {
        getTabList();
    }
}
