package com.gxtc.yyj.newyin.mvp.model.net;

import android.util.Log;

import java.io.IOException;
import java.util.Locale;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoggingInterceptor implements Interceptor {
    private static final String TAG = "LoggingInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();

        long t1 = System.nanoTime();//请求发起的时间
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();//收到响应的时间


        String method = request.method();
        StringBuilder sb = new StringBuilder();
        ;
        if ("POST".equals(method)) {
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
            }
        }

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一 
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        Log.e(TAG,
                String.format(Locale.CHINA, "请求地址：%s%n响应码：%s%n请求参数：%s%n总计耗时%s%n返回json：%s",
                        request.url(),
                        response.code() + " - " + response.message(),
                        sb.toString(),
                        ((t2 - t1) / 1e9d) + " 秒",
                        responseBody.string()
                )
        );
        return response;
    }

}