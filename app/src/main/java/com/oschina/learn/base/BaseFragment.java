package com.oschina.learn.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oschina.learn.utils.InstanceUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;

/**
 * Created by ztw on 2017/3/1.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment{

    protected Context mContext;

    protected View mRoot;

    protected LayoutInflater mInflater;

    protected P mPresenter;

    protected UiProvider uiProvider;

    protected String tag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(getLayoutId(), container, false);
        mInflater = inflater;
        ButterKnife.bind(this, mRoot);
        uiProvider = InstanceUtil.getInstance(UiProvider.class);
        //uiProvider = App.getAppContext().getUiProvider();
        uiProvider.regist(this);
        initPresenter();
        initView(savedInstanceState);
        return mRoot;
    }

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public String getFragmentTag() {
        return tag;
    }

    protected abstract int getLayoutId();

    protected void initTitle(){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiProvider.unregist(this);
        ButterKnife.unbind(this);
    }

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

    protected <T extends View> T findView(int viewId) {
        return (T) mRoot.findViewById(viewId);
    }
}
