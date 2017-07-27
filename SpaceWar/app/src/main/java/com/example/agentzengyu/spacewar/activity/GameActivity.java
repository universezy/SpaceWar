package com.example.agentzengyu.spacewar.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.Bullet;
import com.example.agentzengyu.spacewar.entity.single.Level;
import com.example.agentzengyu.spacewar.service.GameService;
import com.example.agentzengyu.spacewar.view.CircleImageView;
import com.example.agentzengyu.spacewar.view.GameSurfaceView;

import java.util.List;

/**
 * 游戏主界面
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getName();

    private GameSurfaceView mGsv;
    private TextView mTvLife, mTvShield, mTvLaser, mTvLevel, mTvNotify;
    private CircleImageView mCivShield, mCivLaser, mCivShot;

    private SpaceWarApp app = null;
    private PlayerData playerData = null;
    private GameService gameService = null;
    private LevelReceiver levelReceiver;
    private PlayerReceiver playerReceiver;
    private EnemyReceiver enemyReceiver;
    private Handler handlerNotify = new Handler();
    private Handler handlerBullet = new Handler();
    private Runnable runnableNotify = null;
    private Runnable runnableBullet = null;
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
        super.onDestroy();
        handlerNotify.removeCallbacks(runnableNotify);
        handlerBullet.removeCallbacks(runnableBullet);
        unregisterReceiver(levelReceiver);
        unregisterReceiver(playerReceiver);
        unregisterReceiver(enemyReceiver);
        gameService.onStop();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mGsv = (GameSurfaceView) findViewById(R.id.gsv);
        mGsv.setContext(getApplicationContext());
        mTvLife = (TextView) findViewById(R.id.tvLife);
        mTvShield = (TextView) findViewById(R.id.tvShield);
        mTvLaser = (TextView) findViewById(R.id.tvLaser);
        mTvLevel = (TextView) findViewById(R.id.tvLevel);
        mTvNotify = (TextView) findViewById(R.id.tvNotify);
        mCivShield = (CircleImageView) findViewById(R.id.civShield);
        mCivShield.setOnClickListener(this);
        mCivLaser = (CircleImageView) findViewById(R.id.civLaser);
        mCivLaser.setOnClickListener(this);
        mCivShot = (CircleImageView) findViewById(R.id.civShot);
        mCivShot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
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
        });
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        playerData = app.getPlayerData();
        gameService = app.getGameGameService();
        levelReceiver = new LevelReceiver();
        playerReceiver = new PlayerReceiver();
        enemyReceiver = new EnemyReceiver();
        IntentFilter mapFilter = new IntentFilter(Constant.Game.Type.LEVEL);
        registerReceiver(levelReceiver, mapFilter);
        IntentFilter playerFilter = new IntentFilter(Constant.Game.Type.PLAYER);
        registerReceiver(playerReceiver, playerFilter);
        IntentFilter enemyFilter = new IntentFilter(Constant.Game.Type.ENEMY);
        registerReceiver(enemyReceiver, enemyFilter);
        runnableNotify = new Runnable() {
            @Override
            public void run() {
                mTvNotify.setVisibility(View.GONE);
                handlerBullet.postDelayed(runnableBullet, 500);
            }
        };
        runnableBullet = new Runnable() {
            @Override
            public void run() {
                if (shot) {
                    gameService.shotEnemy();
                }
                handlerBullet.postDelayed(runnableBullet, 300);
            }
        };
    }

    /**
     * 开始游戏
     *
     * @param level 地图
     */
    private void startGame(final Level level) {
        mTvLife.setText("" + playerData.getLife().getValue());
        mTvShield.setText("CD: " + playerData.getShield().getValue());
        mTvLaser.setText("CD: " + playerData.getLaser().getValue());
        if (level != null) {
            mTvLevel.setText(level.getMapName());
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
                gameService.openShield();
                break;
            case R.id.civLaser:
                gameService.launchLaser();
                break;
            default:
                break;
        }
    }

    //地图接收器
    public class LevelReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(Constant.BroadCast.TARGET);
            switch (state) {

            }
        }
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

    //敌人接收器
    public class EnemyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(Constant.BroadCast.TARGET);
            switch (state) {
                case Constant.Game.Enemy.BULLET:
                    List<Bullet> bullets = (List<Bullet>) intent.getSerializableExtra(Constant.Game.Enemy.BULLET);
                    break;
                default:
                    break;
            }
        }
    }
}
