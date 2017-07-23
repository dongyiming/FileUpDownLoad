package com.example.down_file_ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.down_file_ui.R;
import com.example.down_file_ui.controller.DownLoadController;
import com.example.down_file_ui.interaction.IDownLoadProgress;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/21 22:35
 */
public class DownLoadActivity extends AppCompatActivity implements View.OnClickListener, IDownLoadProgress {

    private ProgressBar uploadProgress;
    private Button btn;
    private DownLoadController downLoadController;
    private ImageView img_down;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_download_layout);
        uploadProgress = (ProgressBar) findViewById(R.id.pb_upload);
        btn = (Button) findViewById(R.id.btn);
        img_down = (ImageView) findViewById(R.id.img_down);
        btn.setOnClickListener(this);
        initCompant();
    }

    private void initCompant() {

        downLoadController = new DownLoadController(DownLoadActivity.this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn) {

            downLoadController.downLoadFile(this);
        }
    }

    @Override
    public void onProgress(long readByteCount, long totalBytesCount, boolean isDown) {
        uploadProgress.setMax((int) totalBytesCount);
        uploadProgress.setProgress((int) readByteCount);
    }
}
