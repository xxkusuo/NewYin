package com.gxtc.yyj.newyin.mvp.model.net;

import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Jam on 2017/7/18.
 * 请求接口类
 */

public interface IHttpService {
    //https://api.weibo.com/2/statuses/home_timeline.json
    String HOST = "https://api.weibo.com/";
    int TYPE_REFRESH = 0;//刷新
    int TYPE_MORE = 1;//加载更多
    int DEFAULT_RESULT_SIZE = 20;
    int TYPE_NORMAL = 2;//
    int TYPE_CACHE = 3;//第一次读缓存

    @GET("2/statuses/home_timeline.json")
    Observable<ExploreBean> getExplore(
            @Query("access_token") String accessToken,
            @Query("count") int count,
            @Query("page") int page,
            @Query("base_app") int baseApp,
            @Query("feature") int feature,
            @Query("trim_user") int trimUser
    );
}
