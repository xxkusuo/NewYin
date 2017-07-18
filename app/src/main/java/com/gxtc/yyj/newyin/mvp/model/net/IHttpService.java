package com.gxtc.yyj.newyin.mvp.model.net;

import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jam on 2017/7/18.
 * 请求接口类
 */

public interface IHttpService {
    String HOST = "http://gank.io/api/";
    int TYPE_REFRESH = 0;//刷新
    int TYPE_MORE = 1;//加载更多

    @GET("data/{resultSize}/{pageOffset}")
    Observable<ExploreBean> getExplore(@Path("resultSize")String resultSize, @Path("pageOffset")String pageOffset);
}
