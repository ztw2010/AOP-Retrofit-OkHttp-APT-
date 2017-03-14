package com.oschina.learn.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.oschina.learn.base.parceler.Parceler;
import com.oschina.learn.utils.InstanceUtil;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;

/**
 * Created by ztw on 2017/2/28.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity{

    public Context mContext;

    public P mPresenter;

    protected UiProvider uiProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(getLayoutId());
        uiProvider = InstanceUtil.getInstance(UiProvider.class);
        ButterKnife.bind(this);
        Parceler.injectToEntity(this,getIntent());
        mContext = this;
        initToolBar();
        initPresenter();
        initView(savedInstanceState);
    }

    private void initToolBar() {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parceler.injectToBundle(this,outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Parceler.injectToEntity(this,savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDetached();
        ButterKnife.unbind(this);
    }

    public abstract int getLayoutId();

    protected void initPresenter() {
        if (this instanceof BaseView &&
                this.getClass().getGenericSuperclass() instanceof ParameterizedType &&
                ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments().length > 0) {
            Class mPresenterClass = (Class) ((ParameterizedType) (this.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[0];
            mPresenter = InstanceUtil.getInstance(mPresenterClass);
            mPresenter.setView(this);
        }
    }

    public abstract void initView(Bundle savedInstanceState);
}
