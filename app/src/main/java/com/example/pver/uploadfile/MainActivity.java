package com.example.pver.uploadfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.down_file_ui.BaseViewHolder;
import com.example.down_file_ui.ICommonRecyclerListener;
import com.example.down_file_ui.MultiCommonAdapter;

public class MainActivity extends AppCompatActivity implements ICommonRecyclerListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_main);

        initView();
    }

    private void initView() {

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new MultiCommonAdapter(MainActivity.this, this));
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_item_layout;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {

    }

}
