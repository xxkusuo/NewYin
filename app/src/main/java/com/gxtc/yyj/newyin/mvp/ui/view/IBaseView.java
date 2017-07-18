package com.gxtc.yyj.newyin.mvp.ui.view;

import io.reactivex.Observable;

/**
 * Created by Jam on 2017/7/18.
 * UI接口类基类
 */

public interface IBaseView {

    void refreshing();//正在刷新数据

    void error(Observable observable,Throwable throwable);//刷新数据完成

    void refreshComplete();//刷新完成
}
