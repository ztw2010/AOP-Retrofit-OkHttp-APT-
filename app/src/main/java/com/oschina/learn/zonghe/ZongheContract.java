package com.oschina.learn.zonghe;

import com.oschina.learn.base.BasePresenter;
import com.oschina.learn.base.BaseView;

/**
 * Created by ztw on 2017/3/8.
 */

public interface ZongheContract {

    interface View extends BaseView {
        void showTabList(String[] mTabs);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getTabList();
    }

}
