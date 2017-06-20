package com.example.agentzengyu.spacewar.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.adapter.MenuAdapter;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.service.SpaceWarService;

/**
 * 菜单界面
 */
public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getName();
    private SpaceWarApp app = null;
    protected SpaceWarService.ServiceBinder binder;
    private ServiceConnection connection;
    private LinearLayoutManager manager;
    private MenuAdapter adapter;
    private RecyclerView recyclerView;
    private Button mBtnLeft, mBtnRight;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memu);
        initVariable();
        initView();
        startService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter = new MenuAdapter(this);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvMenu);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(position);
        mBtnLeft = (Button) findViewById(R.id.btnLeft);
        mBtnLeft.setOnClickListener(this);
        mBtnRight = (Button) findViewById(R.id.btnRight);
        mBtnRight.setOnClickListener(this);
    }

    /**
     * 启动服务
     */
    private void startService() {
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = (SpaceWarService.ServiceBinder) service;  //获取其实例
                Log.e(SpaceWarService.class.getName(), "Service has started.");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        Intent intent = new Intent(MenuActivity.this, SpaceWarService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLeft:
                if (position > 0) {
                    position--;
                    recyclerView.scrollToPosition(position);
                } else
                    return;
                if (position == 0) {
                    mBtnLeft.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.btnRight:
                if (position < 3) {
                    position++;
                    recyclerView.scrollToPosition(position);
                } else
                    return;
                if (position == 3) {
                    mBtnRight.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                break;
        }
    }
}
