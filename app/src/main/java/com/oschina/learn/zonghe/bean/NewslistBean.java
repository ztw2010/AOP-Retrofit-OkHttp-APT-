package com.oschina.learn.zonghe.bean;

import com.oschina.learn.lib.apt.InstanceFactory;
import com.oschina.learn.zonghe.NewsListRepository;

import java.util.Date;

/**
 * Created by ztw on 2017/3/9.
 */
@InstanceFactory(clazz= NewsListRepository.class)
public class NewslistBean{

    /**
     * 新闻id
     */
    private long id;
    /**
     * 投递者名称
     */
    private String author;
    /**
     * 发布日期
     */
    private Date pubDate;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 投递者编号
     */
    private long authorid;
    /**
     * 评论数
     */
    private int commentCount;
    /**
     * 新闻类型 [0-链接新闻|1-软件推荐|2-讨论区帖子|3-博客|4-普通新闻|7-翻译文章]
     */
    private long type;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NewslistBean{" +
                "author='" + author + '\'' +
                ", id=" + id +
                ", pubDate=" + pubDate +
                ", title='" + title + '\'' +
                ", authorid=" + authorid +
                ", commentCount=" + commentCount +
                ", type=" + type +
                '}';
    }
}
