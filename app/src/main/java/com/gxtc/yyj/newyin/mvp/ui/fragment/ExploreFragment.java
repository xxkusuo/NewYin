package com.gxtc.yyj.newyin.mvp.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;

import com.gxtc.yyj.newyin.R;
import com.gxtc.yyj.newyin.common.base.BaseFragment;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;
import com.gxtc.yyj.newyin.mvp.ui.view.IExploreView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Jam on 2017/7/17.
 */

public class ExploreFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,IExploreView {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_explore;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = findView(R.id.srl_content);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     *刷新方法
     */
    @Override
    public void refreshing() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    /**
     * 刷新完成
     */
    @Override
    public void refreshComplete() {

    }

    /**
     * 返回结果
     * @param requestType
     * @param result
     */
    @Override
    public void onResponse(int requestType, List<ExploreBean.ResultsBean> result) {

    }

    /**
     * 出错
     * @param observable
     * @param throwable
     */
    @Override
    public void error(Observable observable, Throwable throwable) {

    }

    /**
     * SwipeRefreshLayout手动下拉刷新方法
     */
    @Override
    public void onRefresh() {

    }

}
