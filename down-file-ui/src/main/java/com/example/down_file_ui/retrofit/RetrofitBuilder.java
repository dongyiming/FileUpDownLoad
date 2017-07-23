package com.example.down_file_ui.retrofit;

import com.example.down_file_ui.interaction.IDownLoadProgress;
import com.example.down_file_ui.interceptor.DownLoadInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @version : 1.0
 * @Description : 获取retrofit对象
 * @autho : dongyiming
 * @data : 2017/7/14 14:55
 */
public class RetrofitBuilder {

    private static final int DEFAULT_TIMEOUT = 5;
    //本地服务器路径，简单的SSM框架
    public static final String BASE_URL = "http://192.168.1.102:8080/file/";

    private Retrofit retrofit;
    private static RetrofitBuilder instance;
    private OkHttpClient.Builder httpclentBuilder;

    private RetrofitBuilder() {

    }

    public static RetrofitBuilder getInstance() {

        if (instance == null) {
            synchronized (RetrofitBuilder.class) {
                if (instance == null) {
                    instance = new RetrofitBuilder();
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit(IDownLoadProgress iDownLoadProgress) {

        DownLoadInterceptor downLoadInterceptor = new DownLoadInterceptor(iDownLoadProgress);
        httpclentBuilder = new OkHttpClient.Builder()
                .addInterceptor(downLoadInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(httpclentBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
