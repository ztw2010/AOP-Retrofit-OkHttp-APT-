package com.oschina.learn.articledetail.bean;

/**
 * Created by ztw on 2017/3/14.
 */

public class RelativiesBean {
    /**
     * 新闻标题
     */
    private String title;

    /**
     * 新闻原链接
     */
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RelativiesBean{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
