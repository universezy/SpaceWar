package com.example.agentzengyu.spacewar.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.Level;
import com.example.agentzengyu.spacewar.service.GameService;
import com.example.agentzengyu.spacewar.view.CircleImageView;
import com.example.agentzengyu.spacewar.view.GameSurfaceView;

/**
 * 游戏主界面
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private final String TAG = getClass().getName();

    private GameSurfaceView mGsv;
    private TextView mTvNotify;
    private CircleImageView mCivShield, mCivLaser, mCivShot;

    private SpaceWarApp app = null;
    private PlayerData playerData = null;
    private GameService gameService = null;
    private PlayerReceiver playerReceiver;
    private Handler handlerNotify = new Handler();
    private Handler handlerBullet = new Handler();
    private Handler handlerShield = new Handler();
    private Handler handlerLaser = new Handler();
    private Runnable runnableNotify = null;
    private Runnable runnableBullet = null;
    private Runnable runnableShield = null;
    private Runnable runnableLaser = null;
    private boolean init = true;
    private boolean shot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        Level level = (Level) getIntent().getSerializableExtra("Level");
        initView();
        initVariable();
        startGame(level);
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        handlerNotify.removeCallbacks(runnableNotify);
        handlerBullet.removeCallbacks(runnableBullet);
        handlerShield.removeCallbacks(runnableShield);
        handlerLaser.removeCallbacks(runnableLaser);
        unregisterReceiver(playerReceiver);
    }

    @Override
    public void onBackPressed() {
        if (init) return;
        mGsv.stopGame();
        gameService.onStop();
        super.onBackPressed();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mGsv = (GameSurfaceView) findViewById(R.id.gsv);
        mTvNotify = (TextView) findViewById(R.id.tvNotify);
        mCivShield = (CircleImageView) findViewById(R.id.civShield);
        mCivShield.setOnClickListener(this);
        mCivLaser = (CircleImageView) findViewById(R.id.civLaser);
        mCivLaser.setOnClickListener(this);
        mCivShot = (CircleImageView) findViewById(R.id.civShot);
        mCivShot.setOnTouchListener(this);
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        playerData = app.getPlayerData();
        gameService = app.getGameGameService();
        playerReceiver = new PlayerReceiver();
        IntentFilter playerFilter = new IntentFilter(Constant.Game.Type.PLAYER);
        registerReceiver(playerReceiver, playerFilter);
        runnableNotify = new Runnable() {
            @Override
            public void run() {
                init = false;
                mTvNotify.setVisibility(View.GONE);
                handlerBullet.postDelayed(runnableBullet, 500);
            }
        };
        runnableBullet = new Runnable() {
            @Override
            public void run() {
                if (shot) {
                    mGsv.shootEnemy();
                }
                handlerBullet.postDelayed(runnableBullet, 300);
            }
        };
        runnableShield = new Runnable() {
            @Override
            public void run() {
                mCivShield.setClickable(true);
            }
        };
        runnableLaser = new Runnable() {
            @Override
            public void run() {
                mCivLaser.setClickable(true);
            }
        };
    }

    /**
     * 开始游戏
     *
     * @param level 地图
     */
    private void startGame(final Level level) {
        if (level != null) {
            handlerNotify.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameService.onPrepare(level, mGsv);
                }
            }, 1000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civShield:
                mGsv.openShield();
                mCivShield.setClickable(false);
                handlerShield.postDelayed(runnableShield, playerData.getShield().getValue() * 1000);
                break;
            case R.id.civLaser:
                mGsv.openLaser();
                mCivLaser.setClickable(false);
                handlerLaser.postDelayed(runnableLaser, playerData.getLaser().getValue() * 1000);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                shot = true;
                break;
            case MotionEvent.ACTION_UP:
                shot = false;
                break;
            default:
                break;
        }
        return true;
    }

    //玩家接收器
    public class PlayerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(Constant.BroadCast.TARGET);
            switch (state) {
                case Constant.Game.Type.NOTIFY:
                    String msg = intent.getStringExtra(Constant.Game.Type.NOTIFY);
                    boolean status = intent.getBooleanExtra(Constant.Game.Type.STATUS, false);
                    mTvNotify.setText("" + msg);
                    if (status) {
                        handlerNotify.postDelayed(runnableNotify, 600);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
