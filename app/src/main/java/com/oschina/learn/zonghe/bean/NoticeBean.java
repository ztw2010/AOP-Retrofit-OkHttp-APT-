package com.oschina.learn.zonghe.bean;

/**
 * Created by zhongruan on 2017/3/9.
 */

public class NoticeBean {
    /**
     * 未读评论数
     */
    private int replyCount;

    /**
     * 未读私信数
     */
    private int msgCount;

    /**
     * 新增粉丝数
     */
    private int fansCount;

    /**
     * 未读@我数
     */
    private int referCount;

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public int getReferCount() {
        return referCount;
    }

    public void setReferCount(int referCount) {
        this.referCount = referCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public String toString() {
        return "NoticeBean{" +
                "fansCount=" + fansCount +
                ", replyCount=" + replyCount +
                ", msgCount=" + msgCount +
                ", referCount=" + referCount +
                '}';
    }
}
