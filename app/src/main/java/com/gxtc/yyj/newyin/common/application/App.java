package com.gxtc.yyj.newyin.common.application;

import android.app.Application;

import com.gxtc.yyj.newyin.common.utils.Global;

/**
 * Created by Jam on 2017/7/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Global.init(this);//工具类初始化
    }
}
