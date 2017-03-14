package com.oschina.learn.zonghe.bean;

import com.oschina.learn.bean.BaseBean;

import java.util.List;

/**
 * Created by ztw on 2017/3/9.
 */

public class ArticleBean extends BaseBean{

    private List<NewslistBean> newslist;

    private NoticeBean notice;

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "ArticleBean{" +
                "newslist=" + newslist +
                ", notice=" + notice +
                '}';
    }
}


