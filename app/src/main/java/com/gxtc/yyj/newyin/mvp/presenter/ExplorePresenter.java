package com.gxtc.yyj.newyin.mvp.presenter;

import com.gxtc.yyj.newyin.mvp.model.Callback;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;
import com.gxtc.yyj.newyin.mvp.model.net.CommonProtocol;
import com.gxtc.yyj.newyin.mvp.ui.view.IExploreView;

import io.reactivex.Observable;

/**
 * Created by Jam on 2017/7/18.
 */

public class ExplorePresenter{
    private CommonProtocol mProtocol;
    private IExploreView mExploreView;
    public ExplorePresenter(IExploreView baseView) {
        mExploreView = baseView;
        mProtocol = new CommonProtocol();
    }

    public void getExplore(final String resultSize, String pageOffset, int reqType){
        mExploreView.refreshing();
        mProtocol.getExplore(resultSize, pageOffset, new Callback<ExploreBean>() {
            @Override
            public void error(Observable observable, Throwable throwable) {
                mExploreView.refreshComplete();
                mExploreView.error(observable,throwable);
            }

            @Override
            public void onSuccess(int reqType, ExploreBean exploreBean) {
                mExploreView.refreshComplete();
                mExploreView.onResponse(reqType,exploreBean.getResults());//手动转换数据
            }
        }, reqType);
    }
}
