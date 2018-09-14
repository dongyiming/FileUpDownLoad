package com.example.upload_file_ui.service;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/21 21:13
 */
public interface UploadService {

    /**
     * 单文件上传,由于本地服务器没有定义返回数据格式，使用okhttp原始数据流
     *
     * @param file
     * @return
     */
    @Multipart
    @POST("upload")
    Observable<ResponseBody> uploadFile(@Part MultipartBody.Part file);

    /**
     * <上传默认json格式内容>
     *
     * @param:
     * @return:
     * @author: dongyiming
     * @date: 2018/9/14 11:54
     * @version: v1.0
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("upload/file")
    Observable<ResponseBody> uploadFile(@Body RequestBody request);
}
