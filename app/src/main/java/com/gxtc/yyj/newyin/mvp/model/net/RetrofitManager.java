package com.gxtc.yyj.newyin.mvp.model.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jam
 */
public class RetrofitManager {

    private static RetrofitManager instance;

    private RetrofitManager(){
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized(RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                    initRetrofit();
                }
            }
        }
        return instance;
    }

    private static Retrofit mRetrofit;
    private static IHttpService mHttpService;

    public IHttpService getHttpService() {
        return mHttpService;
    }

    private static void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(IHttpService.HOST)
                .client(genericClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mHttpService = mRetrofit.create(IHttpService.class);
    }


    private static OkHttpClient genericClient() {
        LoggingInterceptor loggingInterceptor = null;
        loggingInterceptor = new LoggingInterceptor();
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
//                .addInterceptor(new Interceptor() {
//                                    @Override
//                                    public Response intercept(Chain chain) throws IOException {
//                                        Request request = chain.request();
//                                        HttpUrl.Builder builder = request.url().newBuilder();
//                                        if (MyApplication.isLogin()) {
//                                            UserInfo userInfo = MyApplication.getUserInfo();
//                                            if (userInfo != null) {
//                                                builder.addQueryParameter("userId", userInfo.getUserId());
//                                                builder.addQueryParameter("sessionId", userInfo.getSessionId());
//                                            }
//                                        }
//                                        return chain.proceed(request.newBuilder().url(builder.build()).build());
//                                    }
//                                }
//                )
                .build();
    }
}
