package com.example.down_file_ui.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * @version : 1.0
 * @Description : 文件下载
 * @autho : dongyiming
 * @data : 2017/7/23 0:26
 */
public interface DownLoadService {

    /**
     * 大文件需要加入streaming这个判断，防止下载过程中写入到内存中
     *
     * @param fileName : 项目为了方便只传文件的名字，文件服务器什么的可以用@url传文件路径
     * @return
     */
    @Streaming
    @GET("down")
    Observable<ResponseBody> downloadFile(@Query("fileName") String fileName);
}
