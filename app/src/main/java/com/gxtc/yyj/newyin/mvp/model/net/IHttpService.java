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
    String HOST = "https://api.weibo.com/";
    int TYPE_REFRESH = 0;//刷新
    int TYPE_MORE = 1;//加载更多
    int DEFAULT_RESULT_SIZE = 20;
    int TYPE_NORMAL = 2;//

    @GET("2/statuses/home_timeline.json")
    Observable<ExploreBean> getExplore(
            @Path("access_token") String accessToken,
            @Path("count") int count,
            @Path("page") int page,
            @Path("base_app") int baseApp,
            @Path("feature") int feature,
            @Path("trim_user") int trimUser
    );

}
