package com.oschina.learn.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.oschina.learn.C;
import com.oschina.learn.R;
import com.oschina.learn.base.BaseActivity;
import com.oschina.learn.base.BaseFragment;
import com.oschina.learn.bean.TokenBean;
import com.oschina.learn.faxian.FaxianFragment;
import com.oschina.learn.lib.apt.route.Arg;
import com.oschina.learn.lib.apt.route.Dispatcher;
import com.oschina.learn.lib.apt.route.NonNull;
import com.oschina.learn.mine.MineFragment;
import com.oschina.learn.network.OkHttpUtils;
import com.oschina.learn.network.callback.StringCallback;
import com.oschina.learn.tweet.TweetFragment;
import com.oschina.learn.zonghe.ZongheFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by ztw on 2017/2/24.
 */

@Dispatcher
public class MainAvtivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Arg
    @NonNull
    public TokenBean tokenBean;

    @Bind(R.id.content_container)
    public LinearLayout contentContainer;

    @Bind(R.id.zonghe_container)
    public LinearLayout zongheContainer;

    @Bind(R.id.zonghe_icon)
    public ImageView zongheIcon;

    @Bind(R.id.zonghe_title)
    public TextView zongheTitle;

    @Bind(R.id.tweet_container)
    public LinearLayout tweetContainer;

    @Bind(R.id.tweet_icon)
    public ImageView tweetIcon;

    @Bind(R.id.tweet_title)
    public TextView tweetTitle;

    @Bind(R.id.faxian_container)
    public LinearLayout faxianContainer;

    @Bind(R.id.faxian_icon)
    public ImageView faxianIcon;

    @Bind(R.id.faxian_title)
    public TextView faxianTitle;

    @Bind(R.id.mine_container)
    public LinearLayout mineContainer;

    @Bind(R.id.mine_icon)
    public ImageView mineIcon;

    @Bind(R.id.mine_title)
    public TextView mineTitle;

    @Bind(R.id.dl_main_drawer)
    public DrawerLayout drawerLayout;

    @Bind(R.id.nv_main_navigation)
    public NavigationView navigationView;

    private BaseFragment currentFragment;

    private Short currentModuleIndex = -1;

    private Short historyModuleIndex = -1;

    private Map<String, Object> params = new HashMap<String, Object>();

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT))
            drawerLayout.closeDrawers();
        else super.onBackPressed();
    }

    @OnClick({R.id.zonghe_container, R.id.tweet_container, R.id.faxian_container, R.id.mine_container})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.zonghe_container:
                switchModule(C.KEY.ZONGHE_PAGE);
                break;
            case R.id.tweet_container:
                switchModule(C.KEY.TWEET_PAGE);
                break;
            case R.id.faxian_container:
                switchModule(C.KEY.FAXIAN_PAGE);
                break;
            case R.id.mine_container:
                switchModule(C.KEY.MINE_PAGE);
                break;
        }
    }

    private void switchModule(Short index) {
        if (index.equals(currentModuleIndex)) {
            return;
        }
        selectTab(index);
        currentModuleIndex = index;
        switch (index) {
            case 0:
                ZongheFragment zongheFragment = uiProvider.getUI(ZongheFragment.class);
                if (zongheFragment == null) {
                    zongheFragment = ZongheFragment.getInstance();
                }
                setCurrentModule(zongheFragment);
                break;
            case 1:
                TweetFragment tweetFragment = uiProvider.getUI(TweetFragment.class);
                if (tweetFragment == null) {
                    tweetFragment = TweetFragment.getInstance();
                }
                setCurrentModule(tweetFragment);
                break;
            case 2:
                FaxianFragment faxianFragment = uiProvider.getUI(FaxianFragment.class);
                if (faxianFragment == null) {
                    faxianFragment = FaxianFragment.getInstance();
                }
                setCurrentModule(faxianFragment);
                break;
            case 3:
                MineFragment mineFragment = uiProvider.getUI(MineFragment.class);
                if (mineFragment == null) {
                    mineFragment = MineFragment.getInstance();
                }
                setCurrentModule(mineFragment);
                break;
        }
    }

    private void selectTab(Short index) {
        switch (index) {
            case 0:
                zongheIcon.setSelected(true);
                zongheTitle.setSelected(true);
                tweetIcon.setSelected(false);
                tweetTitle.setSelected(false);
                faxianIcon.setSelected(false);
                faxianTitle.setSelected(false);
                mineIcon.setSelected(false);
                mineTitle.setSelected(false);
                break;
            case 1:
                zongheIcon.setSelected(false);
                zongheTitle.setSelected(false);
                tweetIcon.setSelected(true);
                tweetTitle.setSelected(true);
                faxianIcon.setSelected(false);
                faxianTitle.setSelected(false);
                mineIcon.setSelected(false);
                mineTitle.setSelected(false);
                break;
            case 2:
                zongheIcon.setSelected(false);
                zongheTitle.setSelected(false);
                tweetIcon.setSelected(false);
                tweetTitle.setSelected(false);
                faxianIcon.setSelected(true);
                faxianTitle.setSelected(true);
                mineIcon.setSelected(false);
                mineTitle.setSelected(false);
                break;
            case 3:
                zongheIcon.setSelected(false);
                zongheTitle.setSelected(false);
                tweetIcon.setSelected(false);
                tweetTitle.setSelected(false);
                faxianIcon.setSelected(false);
                faxianTitle.setSelected(false);
                mineIcon.setSelected(true);
                mineTitle.setSelected(true);
                break;
        }
    }

    private void setCurrentModule(BaseFragment baseFragment) {
        hideOrRemoveModuleFragment(currentFragment);
        currentFragment = baseFragment;
        if (contentContainer != null) {
            try {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (baseFragment.isHidden()) {
                    ft.show(baseFragment);
                } else {
                    if (!baseFragment.isAdded()) {
                        ft.add(R.id.content_container, baseFragment);
                    } else {
                        ft.show(baseFragment);
                    }
                }
                ft.commitAllowingStateLoss();
            } catch (Throwable e) {

            }
        }
    }

    private void hideOrRemoveModuleFragment(Fragment module) {
        if (module instanceof ZongheFragment) {
            hideFragment(module);
        } else if (module instanceof TweetFragment) {
            hideFragment(module);
        } else if (module instanceof FaxianFragment) {
            hideFragment(module);
        } else if (module instanceof MineFragment) {
            hideFragment(module);
        }
    }

    private void hideFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public int getLayoutId() {
        return R.layout.avtivity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        params.put("access_token", tokenBean.getAccessToken());
        params.put("dataType", "json");
        if (savedInstanceState == null) {
            switchModule(C.KEY.ZONGHE_PAGE);
        } else {
            historyModuleIndex = savedInstanceState.getShort("historyModuleIndex");// 解决页面重叠的问题
            currentModuleIndex = historyModuleIndex;
        }

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void clearValue() {
        params.clear();
        params.put("access_token", tokenBean.getAccessToken());
        params.put("dataType", "json");
    }

    private void getData(String url, Map<String, Object> params) {
        clearValue();
        params.putAll(params);
        OkHttpUtils.post().url(url).params(params).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.e("TTT", "onError", e);
            }

            @Override
            public void onResponse(String response) {
                Log.d("TTT", "response=" + response);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (currentFragment != null) {
            outState.putString("moduleTag", currentFragment.getFragmentTag());
        }
        outState.putShort("historyModuleIndex", currentModuleIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onNavigationItemSelected(@android.support.annotation.NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_manage)
            Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
        else if (item.getItemId() == R.id.nav_share)
            Toast.makeText(this, "登录", Toast.LENGTH_SHORT).show();
        else if (item.getItemId() == R.id.nav_theme)
            Toast.makeText(this, "主题", Toast.LENGTH_SHORT).show();
        return true;
    }
}
