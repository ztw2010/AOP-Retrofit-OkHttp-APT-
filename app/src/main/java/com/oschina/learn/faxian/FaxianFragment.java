package com.oschina.learn.faxian;

import android.os.Bundle;

import com.oschina.learn.R;
import com.oschina.learn.base.BaseFragment;

/**
 * Created by ztw on 2017/3/2.
 */

public class FaxianFragment extends BaseFragment{

    public static FaxianFragment getInstance() {
        FaxianFragment faxianFragment = new FaxianFragment();
        faxianFragment.tag = "FAXIANFRAGMENT";
        return faxianFragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_faxian;
    }
}
