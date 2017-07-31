package com.gxtc.yyj.newyin.mvp.presenter;

import com.gxtc.yyj.newyin.mvp.greendao.operator.UserDB;
import com.gxtc.yyj.newyin.mvp.model.Callback;
import com.gxtc.yyj.newyin.mvp.model.bean.UserInfo;
import com.gxtc.yyj.newyin.mvp.model.net.CommonProtocol;
import com.gxtc.yyj.newyin.mvp.ui.view.ISplashView;

import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;

import io.reactivex.Observable;

/**
 * Created by Jam on 2017/7/31.
 */

public class UserInfoPresenter {
    private ISplashView mView;
    private CommonProtocol mProtocol;

    public UserInfoPresenter(ISplashView view) {
        mView = view;
        mProtocol = new CommonProtocol();
    }

    public void updateUserInfo(String accessToken, String uid, int reqType) {
        mView.onUpdating();
        mProtocol.updateUserInfo(accessToken, uid, new Callback<UserInfo>() {
            @Override
            public void error(Observable observable, Throwable throwable) {
                mView.onFailed(throwable);
            }

            @Override
            public void onSuccess(int reqType, UserInfo userInfo) {
                updateUserInfo(userInfo);
            }
        }, reqType);
    }


    /**
     * 保存用户信息
     *
     * @param userInfo
     */
    private void updateUserInfo(final UserInfo userInfo) {
        UserDB.updateUserInfo(userInfo, new AsyncOperationListener() {
            @Override
            public void onAsyncOperationCompleted(AsyncOperation operation) {
                mView.onSuccess(userInfo);
            }
        });
    }
}
