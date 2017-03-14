package com.oschina.learn;

/**
 * Created by zhongruan on 2017/2/24.
 */

public class C {

    public static final String BASE_API = "https://www.oschina.net";

    public static final class API{

        public static final String GET_USER_INFO = "/action/openapi/user";

        /**
         * 获取新闻列表
         */
        public static final String GET_NEWS_LISTS = "/action/openapi/news_list";

        /**
         * 获取新闻详情
         */
        public static final String GET_NEWS_DETAIL = "/action/openapi/news_detail";

        /**
         * 获取讨论区的帖子列表(对应android的 问答 分享 综合 职业 站务)
         */
        public static final String GET_POST_LIST = "/action/openapi/post_list";

        /**
         * 获取动弹列表 （最新动弹列表 我的动弹）
         */
        public static final String GET_TWEET_LIST = "/action/openapi/tweet_list";

        /**
         * 获取博客列表
         */
        public static final String GET_BLOG_LIST = "openapi/blog_list";
    }

    public static class KEY{
        /**
         * 综合页面
         */
        public static final Short ZONGHE_PAGE = 0;

        /**
         * 动弹页面
         */
        public static final Short TWEET_PAGE = 1;

        /**
         * 发现页面
         */
        public static final Short FAXIAN_PAGE = 2;

        /**
         * 我的页面
         */
        public static final Short MINE_PAGE = 3;


    }

    public static class Constants{

        public static final int FLAG_MULTI_VH = 0x000001;

        public static final int PAGE_COUNT = 20;

        public static final String VH_CLASS = "vh";
    }


}
