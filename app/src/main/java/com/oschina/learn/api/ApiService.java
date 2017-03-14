package com.oschina.learn.api;

import com.oschina.learn.C;
import com.oschina.learn.articledetail.bean.ArticleDetailBean;
import com.oschina.learn.lib.apt.ApiFactory;
import com.oschina.learn.zonghe.bean.ArticleBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ztw on 2017/3/13.
 */
@ApiFactory
public interface ApiService {

    @GET(C.API.GET_NEWS_LISTS)
    Observable<ArticleBean> getNewsLists(
            @Query("access_token") String accessToken,
            @Query("catalog") int catalog,
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") int pageSize,
            @Query("dataType") String dataType);

    @GET(C.API.GET_NEWS_DETAIL)
    Observable<ArticleDetailBean> getNewsDetail(
            @Query("id") long id,
            @Query("access_token") String access_token,
            @Query("dataType") String dataType);

}
