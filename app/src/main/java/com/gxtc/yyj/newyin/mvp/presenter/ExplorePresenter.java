package com.gxtc.yyj.newyin.mvp.presenter;

import com.gxtc.yyj.newyin.mvp.model.Callback;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;
import com.gxtc.yyj.newyin.mvp.model.net.CommonProtocol;
import com.gxtc.yyj.newyin.mvp.model.net.IHttpService;
import com.gxtc.yyj.newyin.mvp.ui.view.IExploreView;
import com.gxtc.yyj.newyin.sina.Constants;

import io.reactivex.Observable;

/**
 * Created by Jam on 2017/7/18.
 */

public class ExplorePresenter {
    private CommonProtocol mProtocol;
    private IExploreView mExploreView;

    public ExplorePresenter(IExploreView baseView) {
        mExploreView = baseView;
        mProtocol = new CommonProtocol();
    }

    public void getExplore(String accessToken,int page, int reqType) {
        if (reqType == IHttpService.TYPE_REFRESH)
            mExploreView.refreshing();
        mProtocol.getExplore(
                accessToken,
                Constants.DEFAULT_COUNT,
                page,
                Constants.DEFAULT_BASE_APP,
                Constants.DEFAULT_FEATURE,
                Constants.DEFAULT_TRIM_USER,
                new Callback<ExploreBean>() {
            @Override
            public void error(Observable observable, Throwable throwable) {
                mExploreView.refreshComplete();
                mExploreView.error(observable, throwable);
            }

            @Override
            public void onSuccess(int reqType, ExploreBean exploreBean) {
                mExploreView.refreshComplete();
                mExploreView.onResponse(reqType, exploreBean.getStatuses());//手动转换数据
            }
        }, reqType);
    }
}
