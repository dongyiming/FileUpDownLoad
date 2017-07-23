package com.example.upload_file_ui;

/**
 * @version : 1.0
 * @Description : 上传进度
 * @autho : dongyiming
 * @data : 2017/7/21 22:14
 */
public interface IUploadProgress {

    /**
     * 进度
     *
     * @param currentBytesCount
     * @param totalBytesCount
     */
    void onProgress(long currentBytesCount, long totalBytesCount);
}
