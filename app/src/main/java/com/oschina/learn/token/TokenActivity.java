package com.oschina.learn.token;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.oschina.learn.App;
import com.oschina.learn.C;
import com.oschina.learn.R;
import com.oschina.learn.base.parceler.Parceler;
import com.oschina.learn.bean.TokenBean;
import com.oschina.learn.lib.apt.route.Arg;
import com.oschina.learn.lib.apt.route.Dispatcher;
import com.oschina.learn.main.MainAvtivityDispatcher;
import com.oschina.learn.utils.JsonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ztw on 2017/2/28.
 */

@Dispatcher
public class TokenActivity extends Activity{

    @Arg
    public String code;

    @Arg
    public String state;

    @Bind(R.id.web_view)
    public WebView webView;

    private String packageName = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Parceler.injectToEntity(this,getIntent());
        packageName = getPackageName();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        webView.loadUrl(String.format("%s%s", C.BASE_API, "/action/openapi/token?client_id=2grO8DAZmn0FKnAAhjjL&client_secret=nOeYy9oKFBwvJgBYReYgyAZeS3UdkPnT&grant_type=authorization_code&redirect_uri="+packageName+"&code="+code+"&dataType=json"));
        webView.setWebViewClient(new WebViewClient(){

            private boolean isJumped = false;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.local_obj.showSource(''+" + "document.getElementsByTagName('pre')[0].innerHTML+'');");
            }

            @Override
            public void onLoadResource(WebView view, String url) {
            }
        });
    }

    final class InJavaScriptLocalObj {

        @JavascriptInterface
        public void showSource(String html) {
            if(!TextUtils.isEmpty(html)){
                TokenBean tokenBean = new JsonUtil<TokenBean>().json2Bean(html, TokenBean.class.getName());
                App.getAppContext().setTokenBean(tokenBean);
                new MainAvtivityDispatcher(tokenBean).start(TokenActivity.this);
                finish();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(webView.canGoBack())
            {
                webView.goBack();//返回上一页面
                return true;
            }
            else
            {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
