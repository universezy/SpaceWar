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

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.adapter.MenuAdapter;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.service.SpaceWarService;

public class MenuActivity extends AppCompatActivity {
    private SpaceWarApp app = null;
    protected SpaceWarService.ServiceBinder binder;
    private ServiceConnection connection;
    private LinearLayoutManager manager;
    private MenuAdapter adapter;
    private RecyclerView recyclerView;

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

    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter = new MenuAdapter(this);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvMenu);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(1024);
    }

    private void startService() {
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = (SpaceWarService.ServiceBinder) service;  //获取其实例
                Log.e("Service", "Service has started.");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        Intent intent = new Intent(MenuActivity.this, SpaceWarService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
}
