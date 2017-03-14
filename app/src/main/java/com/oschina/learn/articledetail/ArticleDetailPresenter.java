package com.oschina.learn.articledetail;

import android.util.Log;

import com.apt.ApiFactory;
import com.oschina.learn.articledetail.bean.ArticleDetailBean;
import com.oschina.learn.lib.apt.InstanceFactory;

import rx.functions.Action1;

/**
 * Created by ztw on 2017/3/14.
 */

@InstanceFactory
public class ArticleDetailPresenter extends ArticleDetailContract.Presenter {

    @Override
    public void getContent(Long id, String access_token, String dataType) {
        Log.d("TTT", "mCompositeSubscription.isUnsubscribed="+mCompositeSubscription.isUnsubscribed());
        mCompositeSubscription.add(ApiFactory.getNewsDetail(id, access_token, dataType).subscribe(new Action1<ArticleDetailBean>() {
            @Override
            public void call(ArticleDetailBean articleDetailBean) {
                mView.showContent(articleDetailBean);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mView.showError(throwable.getMessage());
            }
        }));
    }

    @Override
    public void onAttached() {

    }
}
