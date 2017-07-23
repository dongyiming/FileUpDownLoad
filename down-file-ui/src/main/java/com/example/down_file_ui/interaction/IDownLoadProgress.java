package com.example.down_file_ui.interaction;

/**
 * @version : 1.0
 * @Description : 上传进度
 * @autho : dongyiming
 * @data : 2017/7/21 22:14
 */
public interface IDownLoadProgress {

    /**
     * @param readByteCount
     * @param totalBytesCount
     * @param isDown
     */
    void onProgress(long readByteCount, long totalBytesCount, boolean isDown);
}
