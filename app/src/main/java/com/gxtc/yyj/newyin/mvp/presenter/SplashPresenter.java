package com.gxtc.yyj.newyin.mvp.presenter;

import com.gxtc.yyj.newyin.common.utils.L;
import com.gxtc.yyj.newyin.mvp.model.Callback;
import com.gxtc.yyj.newyin.mvp.model.bean.AccessTokenBean;
import com.gxtc.yyj.newyin.mvp.model.bean.AuthorizeBean;
import com.gxtc.yyj.newyin.mvp.model.net.CommonProtocol;
import com.gxtc.yyj.newyin.mvp.model.net.IHttpService;
import com.gxtc.yyj.newyin.mvp.ui.view.ISplashView;
import com.gxtc.yyj.newyin.sina.Constants;

import io.reactivex.Observable;

/**
 * Created by Jam on 2017/7/26.
 */

public class SplashPresenter {
    private static final String TAG = "SplashPresenter";
    private CommonProtocol mProtocol;
    private ISplashView mView;

    public SplashPresenter(ISplashView view) {
        mProtocol = new CommonProtocol();
        this.mView = view;
    }

    public void oauth2() {
        mProtocol.authorize(Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE, new Callback<AuthorizeBean>() {
            @Override
            public void error(Observable observable, Throwable throwable) {
                mView.error(throwable);
            }

            @Override
            public void onSuccess(int reqType, AuthorizeBean authorizeBean) {
                L.e(TAG, "code -->" + authorizeBean.getCode());
                mProtocol.accessToken(
                        Constants.APP_KEY,
                        Constants.APP_SECRET,
                        Constants.GRANT_TYPE,
                        authorizeBean.getCode(),
                        Constants.REDIRECT_URL,
                        new Callback<AccessTokenBean>() {
                            @Override
                            public void error(Observable observable, Throwable throwable) {
                                mView.error(throwable);
                            }

                            @Override
                            public void onSuccess(int reqType, AccessTokenBean accessTokenBean) {
                                L.e(TAG, "token -->" + accessTokenBean.getAccessToken());
                                mView.accessTokenSuccess(accessTokenBean);
                            }
                        }, IHttpService.TYPE_NORMAL);
            }
        }, IHttpService.TYPE_NORMAL);
    }
}
