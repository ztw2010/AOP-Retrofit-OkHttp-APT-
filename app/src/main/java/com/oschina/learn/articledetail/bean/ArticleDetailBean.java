package com.oschina.learn.articledetail.bean;

import com.oschina.learn.bean.BaseBean;
import com.oschina.learn.zonghe.bean.NoticeBean;

import java.util.Date;
import java.util.List;

/**
 * Created by ztw on 2017/3/14.
 */

public class ArticleDetailBean extends BaseBean{
    /**
     * 新闻编号
     */
    private Long id;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 新闻内容（HTML）
     */
    private String body;

    /**
     * 发布日期
     */
    private Date pubDate;
    /**
     * 投递者编号
     */
    private Long authorid;
    /**
     * 是否收藏 1-收藏 0-未收藏
     */
    private int favorite;
    /**
     * 评论数
     */
    private int commentCount;
    /**
     * 新闻原地址
     */
    private String url;
    /**
     *
     */
    private NoticeBean notice;

    private List<RelativiesBean> relativies;

    public Long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Long authorid) {
        this.authorid = authorid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public List<RelativiesBean> getRelativies() {
        return relativies;
    }

    public void setRelativies(List<RelativiesBean> relativies) {
        this.relativies = relativies;
    }

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
        return "ArticleDetailBean{" +
                "authorid=" + authorid +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", pubDate=" + pubDate +
                ", favorite=" + favorite +
                ", commentCount=" + commentCount +
                ", url='" + url + '\'' +
                ", notice=" + notice +
                ", relativies=" + relativies +
                '}';
    }
}
