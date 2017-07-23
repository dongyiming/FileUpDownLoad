package com.example.upload_file_ui.http;

import com.example.upload_file_ui.IUploadProgress;

import java.io.File;

import io.reactivex.observers.ResourceObserver;
import okhttp3.ResponseBody;

/**
 *  @Description :
 *  @autho : dongyiming
 *  @version : 1.0
 *  @data : 2017/7/22 0:35
 */
public interface IFileUploadHttpInvoke {
    void uploadFile(File file, ResourceObserver<ResponseBody> resourceObserver, IUploadProgress iUploadProgress);
}
