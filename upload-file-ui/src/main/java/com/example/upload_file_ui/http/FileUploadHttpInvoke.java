package com.example.upload_file_ui.http;

import com.example.upload_file_ui.IUploadProgress;
import com.example.upload_file_ui.ProgressReuqestBody;
import com.example.upload_file_ui.retrofit.RetrofitBuilder;
import com.example.upload_file_ui.service.UploadService;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @version : 1.0
 * @Description : 文件上传的网络请求
 * @autho : dongyiming
 * @data : 2017/7/21 23:50
 */
public class FileUploadHttpInvoke implements IFileUploadHttpInvoke {

    private final RetrofitBuilder retrofitBuilder;

    public FileUploadHttpInvoke() {

        retrofitBuilder = RetrofitBuilder.getInstance();
    }

    /**
     * @param file
     * @param resourceObserver
     * @param iUploadProgress
     */
    @Override
    public void uploadFile(File file, ResourceObserver<ResponseBody> resourceObserver, IUploadProgress iUploadProgress) {


        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), new ProgressReuqestBody(requestBody, iUploadProgress));

        UploadService uploadService = retrofitBuilder.getRetrofit(iUploadProgress).create(UploadService.class);
        uploadService.uploadFile(part)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resourceObserver);
    }
}
