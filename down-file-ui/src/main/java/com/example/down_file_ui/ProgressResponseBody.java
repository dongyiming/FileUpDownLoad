package com.example.down_file_ui;

import android.util.Log;

import com.example.down_file_ui.interaction.IDownLoadProgress;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/23 0:32
 */
public class ProgressResponseBody extends ResponseBody {

    private IDownLoadProgress iDownLoadProgress;
    private BufferedSource bufferedSource;
    private ResponseBody responseBody;


    public ProgressResponseBody(ResponseBody responseBody, IDownLoadProgress iDownLoadProgress) {

        this.responseBody = responseBody;
        this.iDownLoadProgress = iDownLoadProgress;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (null != iDownLoadProgress) {
                    Log.e("dongyiming","byteRead = " + responseBody.contentLength());
                    Log.e("dongyiming","total = " + totalBytesRead);
                    iDownLoadProgress.onProgress(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                }
                return bytesRead;
            }
        };
    }

}
