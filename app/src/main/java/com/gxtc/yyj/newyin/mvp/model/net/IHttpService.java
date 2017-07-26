package com.gxtc.yyj.newyin.mvp.model.net;

import com.gxtc.yyj.newyin.mvp.model.bean.AccessTokenBean;
import com.gxtc.yyj.newyin.mvp.model.bean.AuthorizeBean;
import com.gxtc.yyj.newyin.mvp.model.bean.ExploreBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Jam on 2017/7/18.
 * 请求接口类
 */

public interface IHttpService {
    String HOST = "https://api.weibo.com/";
    int TYPE_REFRESH = 0;//刷新
    int TYPE_MORE = 1;//加载更多
    int DEFAULT_RESULT_SIZE = 10;
    int TYPE_NORMAL = 2;//

    @GET("data/all/{resultSize}/{pageOffset}")
    Observable<ExploreBean> getExplore(@Path("resultSize") int resultSize, @Path("pageOffset") int pageOffset);

    @GET("oauth2/authorize")
    Observable<AuthorizeBean> authorize(
            @Path("client_id") String clientId,
            @Path("redirect_uri") String redirectUri,
            @Path("scope") String scope);

    @POST("oauth2/access_token")
    Observable<AccessTokenBean> accessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grantType,
            @Field("code") String code,
            @Field("redirect_uri") String redirectUri
    );
}
