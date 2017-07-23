package com.example.down_file_ui.interceptor;

import com.example.down_file_ui.ProgressResponseBody;
import com.example.down_file_ui.interaction.IDownLoadProgress;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @version : 1.0
 * @Description : 修改默认拦截器为本地进度
 * @autho : dongyiming
 * @data : 2017/7/21 23:40
 */
public class DownLoadInterceptor implements Interceptor {

    private IDownLoadProgress iDownLoadProgress;

    public DownLoadInterceptor(IDownLoadProgress iDownLoadProgress) {
        this.iDownLoadProgress = iDownLoadProgress;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());

        return originalResponse.newBuilder()
                .body(new ProgressResponseBody(originalResponse.body(), iDownLoadProgress))
                .build();

    }
}
