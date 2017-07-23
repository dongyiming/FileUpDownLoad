package com.example.upload_file_ui;


import android.util.Log;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * @version : 1.0
 * @Description : 自定义请求包装类
 * @autho : dongyiming
 * @data : 2017/7/21 22:13
 */
public class ProgressReuqestBody extends RequestBody {

    private RequestBody requestBody;
    private BufferedSink bufferedSink;
    private final IUploadProgress iUploadProgres;

    public ProgressReuqestBody(RequestBody requestBody, IUploadProgress iUploadProgress) {

        this.requestBody = requestBody;
        this.iUploadProgres = iUploadProgress;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (null == bufferedSink) {
            bufferedSink = Okio.buffer(sink(sink));
        }
        requestBody.writeTo(bufferedSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            //当前写入字节数
            long writtenBytesCount = 0L;
            //总字节长度，避免多次调用contentLength()方法
            long totalBytesCount = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                //增加当前写入的字节数
                writtenBytesCount += byteCount;
                Log.e("dongyiming","writtenBytesCount = " + writtenBytesCount);
                //获得contentLength的值，后续不再调用
                if (totalBytesCount == 0) {
                    totalBytesCount = contentLength();
                    Log.e("dongyiming","totalBytesCount = " + totalBytesCount);
                }
                Observable.just(writtenBytesCount)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer() {
                            @Override
                            public void accept(@NonNull Object o) throws Exception {
                                iUploadProgres.onProgress(writtenBytesCount, totalBytesCount);
                            }
                        });
            }
        };
    }
}
