package com.example.down_file_ui.manager;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.down_file_ui.http.FileDownLoadHttpInvoke;
import com.example.down_file_ui.http.IFileDownLoadHttpInvoke;
import com.example.down_file_ui.interaction.IDownLoadProgress;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

/**
 * @version : 1.0
 * @Description : 属于manager模块，每个lib module的核心功能都存在于manager模块，跟界面隔离,跟httpIvoke和本地sqlite交互
 * @autho : dongyiming
 * @data : 2017/7/22 0:35
 */
public class DownLoadManager {

    private final IFileDownLoadHttpInvoke downLoadHttpInvoke;

    public DownLoadManager() {

        downLoadHttpInvoke = new FileDownLoadHttpInvoke();
    }

    /**
     * 获取服务器返回结果，manager一般做sql存储，并返回给界面展示处理
     *
     * @param iDownLoadProgress
     */
    public void upLoadFile(IDownLoadProgress iDownLoadProgress) {

        String fileName = "andy.jpg";

        ResourceObserver<Bitmap> resourceObserver = new ResourceObserver<Bitmap>() {

            @Override
            public void onNext(@NonNull Bitmap bitmap) {

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("dongyiming", "upLoad file is failed : " + e.toString());

            }

            @Override
            public void onComplete() {
                Log.e("dongyiming", "onComplete");
            }
        };

        downLoadHttpInvoke.uploadFile(fileName, resourceObserver, iDownLoadProgress);
    }
}
