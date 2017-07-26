package com.gxtc.yyj.newyin.mvp.ui.view;

import com.gxtc.yyj.newyin.mvp.model.bean.AccessTokenBean;

/**
 * Created by Jam on 2017/7/26.
 */

public interface ISplashView {

    void error(Throwable throwable);

    void accessTokenSuccess(AccessTokenBean token);
}
