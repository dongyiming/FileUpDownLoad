package com.example.upload_file_ui.manager;

import android.util.Log;

import com.example.upload_file_ui.IUploadProgress;
import com.example.upload_file_ui.http.FileUploadHttpInvoke;
import com.example.upload_file_ui.http.IFileUploadHttpInvoke;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;
import okhttp3.ResponseBody;

/**
 * @version : 1.0
 * @Description : 属于manager模块，每个lib module的核心功能都存在于manager模块，跟界面隔离,跟httpIvoke和本地sqlite交互
 * @autho : dongyiming
 * @data : 2017/7/22 0:35
 */
public class UpLoadManager {

    private final IFileUploadHttpInvoke fileUploadHttpInvoke;

    public UpLoadManager() {

        fileUploadHttpInvoke = new FileUploadHttpInvoke();
    }

    /**
     * 获取服务器返回结果，manager一般做sql存储，并返回给界面展示处理
     *
     * @param iUploadProgress
     */
    public void upLoadFile(IUploadProgress iUploadProgress) {

        File file = new File("/storage/emulated/0/andy.jpg");

        //本地服务器在被上传访问成功后，目前只返回一个"success"字符串
        ResourceObserver<ResponseBody> resourceObserver = new ResourceObserver<ResponseBody>() {

            @Override
            public void onNext(@NonNull ResponseBody responseBody) {

                Reader reader = responseBody.charStream();
                BufferedReader br = new BufferedReader(reader);
                String str;
                try {
                    while ((str = br.readLine()) != null) {

                        Log.e("dongyiming", "ch = " + str);
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        fileUploadHttpInvoke.uploadFile(file, resourceObserver, iUploadProgress);
    }
}
