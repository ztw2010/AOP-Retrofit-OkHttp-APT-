package com.oschina.learn.authorize;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.oschina.learn.C;
import com.oschina.learn.R;
import com.oschina.learn.token.TokenActivityDispatcher;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ztw on 2017/2/28.
 */

/**
 * OpenAPI 授权登录页面
 */
public class AuthorizeActivity extends Activity{

    @Bind(R.id.web_view)
    public WebView webView;

    private String packageName = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        packageName = getPackageName();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.loadUrl(String.format("%s%s", C.BASE_API, "/action/oauth2/authorize?response_type=code&client_id=2grO8DAZmn0FKnAAhjjL&state=xyz&redirect_uri="+packageName));
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
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if(!url.contains("redirect_uri") && url.contains(packageName) && !isJumped){
                    Uri uri = Uri.parse(url);
                    String code = uri.getQueryParameter("code");
                    String state = uri.getQueryParameter("state");
                    isJumped = true;
                    new TokenActivityDispatcher().setCode(code).setState(state).start(AuthorizeActivity.this);
                    finish();
                }
            }

            @Override
            public void onLoadResource(WebView view, String url) {
            }
        });
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
