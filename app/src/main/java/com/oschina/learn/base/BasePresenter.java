package com.oschina.learn.base;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by ztw on 2017/2/28.
 */

public abstract class BasePresenter<V> {
    protected V mView;
    protected CompositeSubscription mCompositeSubscription;

    public void setView(V v) {
        this.mView = v;
        mCompositeSubscription = new CompositeSubscription();
        this.onAttached();
    }

    public abstract void onAttached();

    public void onDetached() {
        mView = null;
        mCompositeSubscription.unsubscribe();
        mCompositeSubscription = null;
    }
}
