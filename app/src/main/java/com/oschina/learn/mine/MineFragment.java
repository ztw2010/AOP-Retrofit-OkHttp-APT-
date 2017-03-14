package com.oschina.learn.mine;

import android.os.Bundle;

import com.oschina.learn.R;
import com.oschina.learn.base.BaseFragment;

/**
 * Created by ztw on 2017/3/2.
 */

public class MineFragment extends BaseFragment{

    public static MineFragment getInstance() {
        MineFragment mineFragment = new MineFragment();
        mineFragment.tag = "MINEFRAGMENT";
        return mineFragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

}
