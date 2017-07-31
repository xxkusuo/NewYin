package com.gxtc.yyj.newyin.mvp.ui.fragment;

import android.animation.Animator;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ProgressBar;

import com.gxtc.yyj.newyin.R;
import com.gxtc.yyj.newyin.common.base.BaseFragment;
import com.gxtc.yyj.newyin.common.utils.DividerItemDecoration;
import com.gxtc.yyj.newyin.common.utils.Global;
import com.gxtc.yyj.newyin.common.utils.L;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;
import com.gxtc.yyj.newyin.mvp.model.net.IHttpService;
import com.gxtc.yyj.newyin.mvp.presenter.ExplorePresenter;
import com.gxtc.yyj.newyin.mvp.ui.adapter.ExploreAdapter;
import com.gxtc.yyj.newyin.mvp.ui.view.IExploreView;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Jam on 2017/7/17.
 */

public class ExploreFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IExploreView {
    private static final String TAG = "ExploreFragment";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<ExploreBean.StatusesBean> mResults;
    private ExplorePresenter mExplorePresenter;
    private int pageOffset = 1;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private int padding = Global.dp2px(8);
    private ExploreAdapter mExploreAdapter;
    private ProgressBar mProgressBar;
    private Oauth2AccessToken mAccessToken;
    private String mTokenStr;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_explore;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = findView(R.id.srl_content);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = findView(R.id.rv_content);
        mProgressBar = findView(R.id.pb_content);
    }

    @Override
    protected void initData() {
        mAccessToken = AccessTokenKeeper.readAccessToken(mActivity);
        mTokenStr = mAccessToken.getToken();
        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mResults = new ArrayList<>();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(padding, Color.parseColor("#fcf7f7"), padding, Color.parseColor("#fcf7f7")));
        mExploreAdapter = new ExploreAdapter(mResults);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mExploreAdapter);
        mExplorePresenter = new ExplorePresenter(this);
        mExplorePresenter.getExplore(IHttpService.TYPE_CACHE);
    }


    @Override
    protected void initListener() {
        super.initListener();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (mResults.size() > 0 && mLayoutManager.findLastVisibleItemPosition() == mResults.size() - 1 && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mExplorePresenter.getExplore(mTokenStr, ++pageOffset, IHttpService.TYPE_MORE);
                }
            }
        });
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
    public void onResponse(int reqType, final List<ExploreBean.StatusesBean> result) {
        switch (reqType) {
            case IHttpService.TYPE_REFRESH:
                notifyRefresh(result);
                break;
            case IHttpService.TYPE_MORE:
                notifyMore(result);
                break;
            case IHttpService.TYPE_CACHE:
                if (result.size() == 0) {
                    mExplorePresenter.getExplore(mTokenStr, pageOffset, IHttpService.TYPE_REFRESH);
                } else {
                    notifyRefresh(result);
                }
                break;
        }
    }

    /**
     * 刷新显示
     *
     * @param result 回调结果
     */
    private void notifyRefresh(List<ExploreBean.StatusesBean> result) {
        mResults.clear();
        mResults.addAll(result);
        mExploreAdapter.notifyItemRangeChanged(0, result.size());
    }

    /**
     * 刷新加载进来的数据 并且做动画
     *
     * @param result 回调结果
     */
    private void notifyMore(final List<ExploreBean.StatusesBean> result) {
        mResults.addAll(result);
        mProgressBar
                .animate()
                .translationY(mProgressBar.getHeight())
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(100)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mProgressBar.setVisibility(View.GONE);
                        mProgressBar.setTranslationY(0);
                        mExploreAdapter.notifyItemRangeChanged(mResults.size(), result.size());
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }

    /**
     * 出错
     *
     * @param observable RxJava对象
     * @param throwable  错误信息
     */
    @Override
    public void error(Observable observable, Throwable throwable) {
        L.e(TAG, throwable.getMessage());
        showToast(throwable.getMessage());
    }

    /**
     * SwipeRefreshLayout手动下拉刷新方法
     */
    @Override
    public void onRefresh() {
        pageOffset = 1;
        mExplorePresenter.getExplore(mTokenStr, pageOffset, IHttpService.TYPE_REFRESH);
    }

}
