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
import android.widget.Toast;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.Config;
import com.example.agentzengyu.spacewar.service.SpaceWarService;


public class LoadingActivity extends AppCompatActivity {
    private ImageView mIvLogo;
    private ProgressBar mPbBasic, mPbPlayer;
    private TextView mTvBasic, mTvPlayer;

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
        mPbBasic = (ProgressBar) findViewById(R.id.pbBasic);
        mPbPlayer = (ProgressBar) findViewById(R.id.pbPlayer);
        mTvBasic = (TextView) findViewById(R.id.tvBasic);
        mTvPlayer = (TextView) findViewById(R.id.tvPlayer);
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        receiver = new LoadingReceiver();
        IntentFilter filter = new IntentFilter(Config.BroadCast.LOADING);
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
        Intent intent = new Intent(LoadingActivity.this, SpaceWarService.class);
        startService(intent);
    }

   public class LoadingReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(Config.BroadCast.STATE);
            Log.e("state", state);
            switch (state) {
                case Config.Status.SHOP:
                    int progressBasic = intent.getIntExtra(Config.Status.PROGRESS, 0);
                    if (progressBasic==-1){
                        Toast.makeText(LoadingActivity.this,"Basic data file damaged. Please reinstall application",Toast.LENGTH_SHORT);
                        finish();
                        return;
                    }
                    mPbBasic.setProgress(progressBasic);
                    if (progressBasic == 100) {
                        mTvBasic.setText(getResources().getString(R.string.loading_basic));
                    }
                    break;
                case Config.Status.PLAYER:
                    int progressPlayer = intent.getIntExtra(Config.Status.PROGRESS, 0);
                    if (progressPlayer==-1){
                        Toast.makeText(LoadingActivity.this,"Player data file damaged. Please reinstall application",Toast.LENGTH_SHORT);
                        return;
                    }
                    mPbPlayer.setProgress(progressPlayer);
                    if (progressPlayer == 100) {
                        mTvPlayer.setText(getResources().getString(R.string.loading_player));
                        handler.postDelayed(runnable, 800);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
