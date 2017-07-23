package com.example.upload_file_ui.interceptor;

import com.example.upload_file_ui.IUploadProgress;
import com.example.upload_file_ui.ProgressReuqestBody;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @version : 1.0
 * @Description : 修改默认拦截器为本地进度
 * @autho : dongyiming
 * @data : 2017/7/21 23:40
 */
public class UploadInterceptor implements Interceptor {

    private IUploadProgress iUploadProgress;

    public UploadInterceptor(IUploadProgress iUploadProgress) {
        this.iUploadProgress = iUploadProgress;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        if (request == null) {
            return chain.proceed(request);
        }
        Request build = request.newBuilder().method(request.method(), new ProgressReuqestBody(request.body(), iUploadProgress)).build();

        return chain.proceed(build);
    }
}
