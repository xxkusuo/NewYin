package com.gxtc.yyj.newyin.mvp.model;

import io.reactivex.Observable;

/**
 * Created by Jam on 2017/7/11.
 */

public interface Callback<T> {
    void error(Observable observable, Throwable throwable);
    void onSuccess(int reqType,T t);
}
