package com.gxtc.yyj.newyin.mvp.ui.fragment;

import android.animation.Animator;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Call;

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
    private int padding = Global.dp2px(12);
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

        OkHttpUtils.post().url("https://service.plutuspay.com/invoice")
                .addParams("sn","98261711361364")
                .addParams("scanText","http://w.url.cn/s/AoOkoCq")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                L.e(TAG,e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                L.e(TAG,response);
            }
        });
    }

    @Override
    protected void initData() {
        mAccessToken = AccessTokenKeeper.readAccessToken(mActivity);
        mTokenStr = mAccessToken.getToken();
        L.e(TAG, "token -->" + mTokenStr);
        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mResults = new ArrayList<>();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(padding, Color.parseColor("#f1efef"), padding, Color.parseColor("#f1efef")));
        mExploreAdapter = new ExploreAdapter(mResults);
        mRecyclerView.setAdapter(mExploreAdapter);
        mExplorePresenter = new ExplorePresenter(this);
//        mExplorePresenter.getExplore(mTokenStr, pageOffset, IHttpService.TYPE_REFRESH);
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
    public void onResponse(int reqType, List<ExploreBean.StatusesBean> result) {
        switch (reqType) {
            case IHttpService.TYPE_REFRESH:
                mResults.clear();
                mResults.addAll(result);
                break;
            case IHttpService.TYPE_MORE:
                mResults.addAll(result);
                break;
        }
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
                        mExploreAdapter.notifyDataSetChanged();
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
