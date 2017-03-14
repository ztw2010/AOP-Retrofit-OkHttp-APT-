package com.oschina.learn.articledetail;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.oschina.learn.App;
import com.oschina.learn.R;
import com.oschina.learn.articledetail.bean.ArticleDetailBean;
import com.oschina.learn.base.BaseActivity;
import com.oschina.learn.lib.apt.route.Arg;
import com.oschina.learn.lib.apt.route.Dispatcher;
import com.oschina.learn.lib.apt.route.NonNull;

import butterknife.Bind;

/**
 * Created by ztw on 2017/3/14.
 */
@Dispatcher
public class ArticleDetailActivity extends BaseActivity<ArticleDetailPresenter> implements ArticleDetailContract.View{

    @Arg
    @NonNull
    public Long articleId;

    @Bind(R.id.content_web)
    WebView contentWeb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mPresenter.getContent(articleId, App.getAppContext().getTokenBean().getAccessToken(), "json");
    }

    @Override
    public void showContent(ArticleDetailBean articleDetailBean) {
        contentWeb.loadDataWithBaseURL("about:blank", articleDetailBean.getBody(),"text/html", "utf-8", null);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, "请求出错,message="+message, Toast.LENGTH_SHORT).show();
    }
}
