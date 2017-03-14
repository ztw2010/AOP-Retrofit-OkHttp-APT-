package com.oschina.learn.tweet;

import com.oschina.learn.base.BasePresenter;
import com.oschina.learn.base.BaseView;

/**
 * Created by zhongruan on 2017/3/10.
 */

public interface TweetContract {

    interface View extends BaseView {
        void showTabList(String[] mTabs);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getTabList();
    }
}
