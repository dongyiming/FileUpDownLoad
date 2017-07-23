package com.example.down_file_ui.http;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.down_file_ui.interaction.IDownLoadProgress;
import com.example.down_file_ui.retrofit.RetrofitBuilder;
import com.example.down_file_ui.service.DownLoadService;
import com.example.down_file_ui.utils.FileUtils;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @version : 1.0
 * @Description : 文件上传的网络请求
 * @autho : dongyiming
 * @data : 2017/7/21 23:50
 */
public class FileDownLoadHttpInvoke implements IFileDownLoadHttpInvoke {

    private final RetrofitBuilder retrofitBuilder;

    public FileDownLoadHttpInvoke() {

        retrofitBuilder = RetrofitBuilder.getInstance();
    }

    /**
     * @param fileName
     * @param resourceObserver
     * @param iDownLoadProgress
     */
    @Override
    public void uploadFile(String fileName, ResourceObserver<Bitmap> resourceObserver, IDownLoadProgress iDownLoadProgress) {


        DownLoadService uploadService = retrofitBuilder.getRetrofit(iDownLoadProgress).create(DownLoadService.class);
        uploadService.downloadFile(fileName)
                .map(new Function<ResponseBody, Bitmap>() {

                    @Override
                    public Bitmap apply(@NonNull ResponseBody responseBody) throws Exception {
                        File file = FileUtils.saveFile(responseBody);
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                        return bitmap;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resourceObserver);
    }
}
