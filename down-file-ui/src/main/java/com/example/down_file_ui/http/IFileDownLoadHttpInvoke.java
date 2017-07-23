package com.example.down_file_ui.http;

import android.graphics.Bitmap;

import com.example.down_file_ui.interaction.IDownLoadProgress;

import io.reactivex.observers.ResourceObserver;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/22 0:35
 */
public interface IFileDownLoadHttpInvoke {
    void uploadFile(String url, ResourceObserver<Bitmap> resourceObserver, IDownLoadProgress iDownLoadProgress);
}
