package com.gxtc.yyj.newyin.common.application;

import android.app.Application;

import com.gxtc.yyj.newyin.common.utils.Global;
import com.gxtc.yyj.newyin.mvp.greendao.operator.DBUtil;
import com.gxtc.yyj.newyin.sina.Constants;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

/**
 * Created by Jam on 2017/7/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Global.init(this);//工具类初始化
        /**
         * 新浪微博初始化
         */
        WbSdk.install(this, new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL,
                Constants.SCOPE));
        /**
         * 数据库工具类初始化
         */
        DBUtil.init(this);
    }
}
