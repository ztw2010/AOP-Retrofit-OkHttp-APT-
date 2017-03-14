package com.oschina.learn.articledetail;

import com.oschina.learn.articledetail.bean.ArticleDetailBean;
import com.oschina.learn.base.BasePresenter;
import com.oschina.learn.base.BaseView;

/**
 * Created by ztw on 2017/3/14.
 */

public interface ArticleDetailContract {

    interface View extends BaseView {
        void showContent(ArticleDetailBean articleDetailBean);

        void showError(String message);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getContent(Long id, String access_token, String dataType);
    }
}
