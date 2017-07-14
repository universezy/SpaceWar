package com.example.agentzengyu.spacewar.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.service.LoaderService;

/**
 * 加载页面
 */
public class LoadingActivity extends AppCompatActivity {
    private ImageView mIvLogo;
    private ProgressBar mPbLoad;
    private TextView mTvLoad;

    private LoadingReceiver receiver;
    private Handler handler= new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);
        initView();
        initVariable();
        startService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mIvLogo = (ImageView) findViewById(R.id.ivLogo);
        mPbLoad = (ProgressBar) findViewById(R.id.pbLoad);
        mTvLoad = (TextView) findViewById(R.id.tvLoad);
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        receiver = new LoadingReceiver();
        IntentFilter filter = new IntentFilter(Constant.BroadCast.LOADING);
        registerReceiver(receiver, filter);
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intentMenu = new Intent(LoadingActivity.this, MenuActivity.class);
                startActivity(intentMenu);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                finish();
            }
        };
    }

    /**
     * 启动服务
     */
    private void startService() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, LoaderService.class);
                startService(intent);
            }
        }, 2000);
    }


    public class LoadingReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(Constant.BroadCast.STATE);
            Log.e("start","===");
            handler.postDelayed(runnable,1000);
        }
    }
}
