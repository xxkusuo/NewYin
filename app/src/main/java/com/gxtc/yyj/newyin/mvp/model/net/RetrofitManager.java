package com.gxtc.yyj.newyin.mvp.model.net;

import com.gxtc.yyj.newyin.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
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
                .build();
        mHttpService = mRetrofit.create(IHttpService.class);
    }


    private static OkHttpClient genericClient() {
        LoggingInterceptor loggingInterceptor = null;
        if (BuildConfig.DEBUG) {//可以在这里设置是否开启日志
        loggingInterceptor = new LoggingInterceptor();
        }
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
