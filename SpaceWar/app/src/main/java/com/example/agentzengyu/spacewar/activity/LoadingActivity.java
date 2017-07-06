package com.example.agentzengyu.spacewar.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private Handler handler;
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
        handler = new Handler();
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
            int progress = intent.getIntExtra(Constant.Status.PROGRESS, 0);
            if (progress == -1) {
                Toast.makeText(LoadingActivity.this, "Resource file damaged. Initializing player data.", Toast.LENGTH_SHORT);
                finish();
                return;
            }
            switch (state) {
                case Constant.Status.SHOP:
                    mTvLoad.setText(getResources().getString(R.string.loading_shop));
                    mPbLoad.setProgress(progress);
                    if (progress == 100) {
                        mTvLoad.setText(getResources().getString(R.string.loaded_shop));
                    }
                    break;
                case Constant.Status.PLAYER:
                    mTvLoad.setText(getResources().getString(R.string.loading_player));
                    mPbLoad.setProgress(progress);
                    if (progress == 100) {
                        mTvLoad.setText(getResources().getString(R.string.loaded_player));
                        handler.postDelayed(runnable, 800);
                    }
                    break;
                case Constant.Status.MAP:
                    mTvLoad.setText(getResources().getString(R.string.loading_map));
                    mPbLoad.setProgress(progress);
                    if (progress == 100) {
                        mTvLoad.setText(getResources().getString(R.string.loaded_map));
                    }
                    break;
                case Constant.Status.ENEMY:
                    mTvLoad.setText(getResources().getString(R.string.loading_enemy));
                    mPbLoad.setProgress(progress);
                    if (progress == 100) {
                        mTvLoad.setText(getResources().getString(R.string.loaded_enemy));
                        handler.postDelayed(runnable, 800);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
