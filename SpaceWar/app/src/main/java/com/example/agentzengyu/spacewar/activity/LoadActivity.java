package com.example.agentzengyu.spacewar.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.service.LoaderService;

/**
 * 加载页面
 */
public class LoadActivity extends AppCompatActivity {
    private SpaceWarApp app = null;
    private LoadingReceiver receiver;
    private Handler handler ;
    private Runnable runnable;

    private ProgressBar mPbLoad;
    private TextView mTvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_load);
        initView();
        initVariable();
        startService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        app.destroyInitService();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onBackPressed() {

    }

    /**
     * 初始化布局
     */
    private void initView() {
        mPbLoad = (ProgressBar) findViewById(R.id.pbLoad);
        mTvLoad = (TextView) findViewById(R.id.tvLoad);
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        receiver = new LoadingReceiver();
        IntentFilter filter = new IntentFilter(Constant.Init.TAG);
        registerReceiver(receiver, filter);
        handler= new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intentMenu = new Intent(LoadActivity.this, MenuActivity.class);
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
                Intent intent = new Intent(LoadActivity.this, LoaderService.class);
                startService(intent);
            }
        }, 2000);
    }

    public class LoadingReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String target = intent.getStringExtra(Constant.BroadCast.TARGET);
            switch (target) {
                case Constant.Init.Type.ARTICLE:
                    mPbLoad.setProgress(16);
                    break;
                case Constant.Init.Type.PLAYER:
                    mPbLoad.setProgress(32);
                    break;
                case Constant.Init.Type.ENEMY:
                    mPbLoad.setProgress(48);
                    break;
                case Constant.Init.Type.LEVEL:
                    mPbLoad.setProgress(64);
                    break;
                case Constant.Init.Type.RELEVANCY:
                    mPbLoad.setProgress(80);
                    break;
                case Constant.Init.Type.BIND:
                    mPbLoad.setProgress(100);
                    mTvLoad.setText(R.string.load_end);
                    handler.postDelayed(runnable, 1000);
                    break;
                case Constant.Init.Type.ERROR:
                    Toast.makeText(LoadActivity.this, R.string.load_error, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
