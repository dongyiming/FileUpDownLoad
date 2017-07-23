package com.example.upload_file_ui.controller;

import android.content.Context;

import com.example.upload_file_ui.IUploadProgress;
import com.example.upload_file_ui.manager.UpLoadManager;

/**
 * @version : 1.0
 * @Description : 界面回调，调用manager层逻辑完成界面数据适配，界面简单所以没看到具体作用
 * @autho : dongyiming
 * @data : 2017/7/21 23:00
 */
public class UploadController {

    private Context mContext;
    private final UpLoadManager upLoadManager;

    public UploadController(Context mContext) {

        this.mContext = mContext;
        upLoadManager = new UpLoadManager();
    }

    public void upLoadFile(IUploadProgress iUploadProgress) {

        upLoadManager.upLoadFile(iUploadProgress);

    }
}
