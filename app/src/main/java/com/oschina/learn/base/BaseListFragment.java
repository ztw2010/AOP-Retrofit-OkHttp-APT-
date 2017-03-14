package com.oschina.learn.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oschina.learn.App;
import com.oschina.learn.C;
import com.oschina.learn.bean.TokenBean;
import com.oschina.learn.utils.InstanceUtil;
import com.oschina.learn.widget.TRecyclerView;

public class BaseListFragment extends Fragment {

    private TRecyclerView mXRecyclerView;

    private TokenBean tokenBean;

    /**
     * @param vh 传入VH的类名
     * @return
     */
    public static BaseListFragment newInstance(Class<? extends BaseViewHolder> vh, String type) {
        Bundle arguments = new Bundle();
        arguments.putString(C.Constants.VH_CLASS, vh.getCanonicalName());
        arguments.putString("type",type);
        BaseListFragment fragment = new BaseListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tokenBean = App.getAppContext().getTokenBean();
        mXRecyclerView = new TRecyclerView(getContext())
                .setParam("access_token", tokenBean.getAccessToken())
                .setParam("catalog",Integer.valueOf(getArguments().getString("type")))
                .setParam("pageIndex", 1)
                .setParam("pageSize", C.Constants.PAGE_COUNT)
                .setParam("dataType", "json")
                .setView(InstanceUtil.forName(getArguments().getString(C.Constants.VH_CLASS)));
        return mXRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mXRecyclerView != null) mXRecyclerView.fetch();
    }

}