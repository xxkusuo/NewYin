package com.gxtc.yyj.newyin.mvp.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;

import com.gxtc.yyj.newyin.R;
import com.gxtc.yyj.newyin.common.base.BaseFragment;

/**
 * Created by Jam on 2017/7/17.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = findView(R.id.srl_main);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * SwipeRefreshLayout刷新方法
     */
    @Override
    public void onRefresh() {

    }
}
