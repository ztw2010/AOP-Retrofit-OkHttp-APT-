package com.oschina.learn.zonghe;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.oschina.learn.R;
import com.oschina.learn.base.BaseFragment;
import com.oschina.learn.base.BaseListFragment;

import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by ztw on 2017/3/1.
 */

public class ZongheFragment extends BaseFragment<ZonghePresenter> implements ZongheContract.View {

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Bind(R.id.toolbar_iv_target)
    ImageView mIvTarget;

    @Bind(R.id.toolbar_iv_outgoing)
    ImageView mIvOutgoing;

    private AppCompatActivity appCompatActivity;

    public static ZongheFragment getInstance() {
        ZongheFragment zongheFragment = new ZongheFragment();
        zongheFragment.tag = "ZONGHEFRAGMENT";
        return zongheFragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        appCompatActivity = (AppCompatActivity) getActivity();
        return R.layout.fragment_zonghe;
    }

    @Override
    public void showTabList(final String[] mTabs) {
        Observable.from(mTabs).map(new Func1<String, BaseListFragment>() {
            @Override
            public BaseListFragment call(String s) {
                return BaseListFragment.newInstance(ArticleItemVH.class, s);
            }
        }).toList().map(new Func1<List<BaseListFragment>, FragmentAdapter>() {
            @Override
            public FragmentAdapter call(List<BaseListFragment> articleFragments) {
                return FragmentAdapter.newInstance(appCompatActivity.getSupportFragmentManager(), articleFragments, mTabs);
            }
        })
                .subscribe(new Subscriber<FragmentAdapter>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FragmentAdapter fragmentAdapter) {
                        viewpager.setAdapter(fragmentAdapter);
                    }
                });
        PagerChangeListener mPagerChangeListener = PagerChangeListener.newInstance(collapsingToolbar, mIvTarget, mIvOutgoing);
        viewpager.addOnPageChangeListener(mPagerChangeListener);
        tabLayout.setupWithViewPager(viewpager);
    }
}
