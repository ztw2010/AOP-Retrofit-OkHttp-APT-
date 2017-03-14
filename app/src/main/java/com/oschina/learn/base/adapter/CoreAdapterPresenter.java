package com.oschina.learn.base.adapter;

import android.util.Log;

import com.oschina.learn.bean.Repository;
import com.oschina.learn.zonghe.bean.ArticleBean;

import java.util.HashMap;
import java.util.Map;

import rx.functions.Action1;

/**
 * Created by ztw on 16/12/27.
 */

public class CoreAdapterPresenter<T extends Repository> {
    private T mRepository;//仓库
    private Map<String, Object> param = new HashMap<String, Object>();//设置仓库钥匙
    private int begin = 0;
    private final IAdapterView view;

    public interface IAdapterView {
        void setEmpty();

        void setData(ArticleBean response, int begin);

        void reSetEmpty();
    }

    public CoreAdapterPresenter(IAdapterView mIAdapterViewImpl) {
        this.view = mIAdapterViewImpl;
    }

    public void setRepository(T mRepository) {
        this.mRepository = mRepository;
    }

    public CoreAdapterPresenter setParam(String key, Object value) {
        this.param.put(key, value);
        return this;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public void fetch() {
        begin++;
        view.reSetEmpty();
        if (mRepository == null) {
            Log.e("mRepository", "null");
            return;
        }
        mRepository.param = param;//设置仓库钥匙
        mRepository.getPageAt2(begin).subscribe(new Action1() {
            @Override
            public void call(Object res) {
                view.setData((ArticleBean) res, begin);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                view.setEmpty();
            }
        });
    }
}
