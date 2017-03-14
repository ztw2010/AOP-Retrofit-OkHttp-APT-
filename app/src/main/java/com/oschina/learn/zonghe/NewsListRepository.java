package com.oschina.learn.zonghe;

import com.apt.ApiFactory;
import com.oschina.learn.bean.DataArr;
import com.oschina.learn.bean.Repository;
import com.oschina.learn.zonghe.bean.ArticleBean;

import rx.Observable;

/**
 * Created by ztw on 2017/3/13.
 */

/**
 * 新闻列表页仓库
 */
public class NewsListRepository extends Repository<ArticleBean>{

    @Override
    public Observable<DataArr<ArticleBean>> getPageAt(int page) {
        return null;
    }

    @Override
    public Observable<ArticleBean> getPageAt2(int page) {
        return ApiFactory.getNewsLists(
                String.valueOf(param.get("access_token")),
                Integer.valueOf((Integer) param.get("catalog")),
                Integer.valueOf((Integer) param.get("pageIndex")),
                Integer.valueOf((Integer) param.get("pageSize")),
                String.valueOf(param.get("dataType")));
    }
}
