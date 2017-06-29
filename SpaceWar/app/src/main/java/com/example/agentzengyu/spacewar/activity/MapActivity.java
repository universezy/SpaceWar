package com.example.agentzengyu.spacewar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.adapter.MapAdapter;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;

/**
 * 地图选择界面
 */
public class MapActivity extends AppCompatActivity {
    private final String TAG = getClass().getName();
    private SpaceWarApp app = null;
    private LinearLayoutManager manager;
    private MapAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map);
        initVariable();
        initView();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new MapAdapter(this, app.getMapData());
    }

    /**
     * 初始化布局
     */
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvMap);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }
}
