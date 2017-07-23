package com.example.upload_file_ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.upload_file_ui.IUploadProgress;
import com.example.upload_file_ui.R;
import com.example.upload_file_ui.controller.UploadController;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/21 22:35
 */
public class UploadActivity extends AppCompatActivity implements View.OnClickListener, IUploadProgress {

    private ProgressBar uploadProgress;
    private Button btn;
    private UploadController upLoadController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload_layout);
        uploadProgress = (ProgressBar) findViewById(R.id.pb_upload);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        initCompant();
    }

    private void initCompant() {

        upLoadController = new UploadController(UploadActivity.this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn) {

            upLoadController.upLoadFile(this);
        }
    }

    @Override
    public void onProgress(long currentBytesCount, long totalBytesCount) {

        Log.e("dongyiming", "currentBytesCount = " + currentBytesCount + " /n" + "totalBytesCount = " + totalBytesCount);
        uploadProgress.setMax((int) totalBytesCount);
        uploadProgress.setProgress((int) currentBytesCount);

    }
}
