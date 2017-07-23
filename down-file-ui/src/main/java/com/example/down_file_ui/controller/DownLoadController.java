package com.example.down_file_ui.controller;

import android.content.Context;

import com.example.down_file_ui.interaction.IDownLoadProgress;
import com.example.down_file_ui.manager.DownLoadManager;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/23 1:25
 */
public class DownLoadController {

    private Context mContext;
    private final DownLoadManager downLoadManager;

    public DownLoadController(Context mContext) {

        this.mContext = mContext;
        downLoadManager = new DownLoadManager();
    }

    public void downLoadFile(IDownLoadProgress iDownLoadProgress) {

        downLoadManager.upLoadFile(iDownLoadProgress);

    }
}
