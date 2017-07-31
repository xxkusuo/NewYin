package com.gxtc.yyj.newyin.mvp.ui.view;

import com.gxtc.yyj.newyin.mvp.model.bean.UserInfo;

/**
 * Created by Jam on 2017/7/31.
 */

public interface ISplashView {
    void onUpdating();
    void onSuccess(UserInfo userInfo);
    void onFailed(Throwable throwable);
}
