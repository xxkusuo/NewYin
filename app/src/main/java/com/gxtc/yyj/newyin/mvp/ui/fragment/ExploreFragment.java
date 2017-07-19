package com.gxtc.yyj.newyin.mvp.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gxtc.yyj.newyin.R;
import com.gxtc.yyj.newyin.common.base.BaseFragment;
import com.gxtc.yyj.newyin.common.utils.DividerItemDecoration;
import com.gxtc.yyj.newyin.common.utils.Global;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;
import com.gxtc.yyj.newyin.mvp.model.net.IHttpService;
import com.gxtc.yyj.newyin.mvp.presenter.ExplorePresenter;
import com.gxtc.yyj.newyin.mvp.ui.adapter.ExploreAdapter;
import com.gxtc.yyj.newyin.mvp.ui.view.IExploreView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Jam on 2017/7/17.
 */

public class ExploreFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IExploreView {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<ExploreBean.ResultsBean> mResults;
    private ExplorePresenter mExplorePresenter;
    private int pageOffset = 1;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private int padding = Global.dp2px(12);
    private int lastVisibleItem;
    private ExploreAdapter mExploreAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_explore;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = findView(R.id.srl_content);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = findView(R.id.rv_content);
        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(padding, padding, getResources().getColor(R.color.color_bg_gray)));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if ((newState == RecyclerView.SCROLL_STATE_SETTLING
                        || newState == RecyclerView.SCROLL_STATE_DRAGGING)
                        && mLayoutManager.findLastVisibleItemPosition() >= mResults.size() - 5) {//需要大于5第一次的数据
                    mExplorePresenter.getExplore(++pageOffset, IHttpService.TYPE_MORE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected void initData() {
        mResults = new ArrayList<>();
        mExplorePresenter = new ExplorePresenter(this);
        mExplorePresenter.getExplore(pageOffset, IHttpService.TYPE_REFRESH);
        mExploreAdapter = new ExploreAdapter(mResults);
        mRecyclerView.setAdapter(mExploreAdapter);
    }

    /**
     * 刷新方法
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
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 返回结果
     *
     * @param reqType 请求类型
     * @param result  返回结果
     */
    @Override
    public void onResponse(int reqType, List<ExploreBean.ResultsBean> result) {
        switch (reqType) {
            case IHttpService.TYPE_REFRESH:
                mResults.clear();
                mResults.addAll(result);
                break;
            case IHttpService.TYPE_MORE:
                mResults.addAll(result);
                break;
        }
        mExploreAdapter.notifyDataSetChanged();
    }

    /**
     * 出错
     *
     * @param observable RxJava对象
     * @param throwable  错误信息
     */
    @Override
    public void error(Observable observable, Throwable throwable) {
        showToast(throwable.getMessage());
    }

    /**
     * SwipeRefreshLayout手动下拉刷新方法
     */
    @Override
    public void onRefresh() {
        pageOffset = 1;
        mExplorePresenter.getExplore(pageOffset, IHttpService.TYPE_REFRESH);
    }

}
