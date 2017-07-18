package com.gxtc.yyj.newyin.mvp.model.net;

import com.gxtc.yyj.newyin.mvp.model.Callback;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jam on 2017/7/18.
 */

public class BaseProtocol {
    public IHttpService getIHttpService() {
        return RetrofitManager.getInstance().getHttpService();
    }

    /**
     * 请求方法
     *
     * @param <T>
     */
    public <T> void execute(final Observable<T> observable, final Callback<T> callback, final int reqType) {
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(@NonNull T t) throws Exception {
                        callback.onSuccess(reqType, t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        callback.error(observable, throwable);
                    }
                });
    }
}
