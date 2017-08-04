package com.example.agentzengyu.spacewar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.adapter.LevelAdapter;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.view.CircleImageView;

/**
 * 地图选择界面
 */
public class LevelActivity extends AppCompatActivity {
    private SpaceWarApp app = null;
    private LinearLayoutManager manager;
    private LevelAdapter adapter;
    private ImageView mIvChoose;
    private CircleImageView mCivReturn;
    private RecyclerView mRvLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level);
        initVariable();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(0, R.anim.activity_out);
    }

    @Override
    public void onBackPressed() {

    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new LevelAdapter(this, app.getLevelLibrary());
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mIvChoose = (ImageView)findViewById(R.id.ivChoose);
        mCivReturn = (CircleImageView)findViewById(R.id.civReturn);
        mCivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRvLevels = (RecyclerView) findViewById(R.id.rvMap);
        new PagerSnapHelper().attachToRecyclerView(mRvLevels);
        mRvLevels.setLayoutManager(manager);
        mRvLevels.setAdapter(adapter);
        mRvLevels.scrollToPosition(0);
    }
}
