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
import com.example.agentzengyu.spacewar.entity.single.MapItem;
import com.example.agentzengyu.spacewar.service.GameService;
import com.example.agentzengyu.spacewar.view.BulletEnemyView;
import com.example.agentzengyu.spacewar.view.BulletPlayerView;
import com.example.agentzengyu.spacewar.view.CircleImageView;
import com.example.agentzengyu.spacewar.view.EnemyView;
import com.example.agentzengyu.spacewar.view.MapView;
import com.example.agentzengyu.spacewar.view.PlayerView;

import java.util.List;

/**
 * 游戏主界面
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getName();

    private MapView mapView;
    private PlayerView playerView;
    private EnemyView enemyView;
    private BulletPlayerView bulletPlayerView;
    private BulletEnemyView bulletEnemyView;
    private TextView mTvLife, mTvShield, mTvLaser, mTvMap, mTvNotify;
    private CircleImageView mCivShield, mCivLaser, mCivShot;

    private SpaceWarApp app = null;
    private PlayerData playerData = null;
    private GameService service = null;
    private MapReceiver mapReceiver;
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
        MapItem mapItem = (MapItem) getIntent().getSerializableExtra("MapItem");
        initView();
        initVariable();
        startGame(mapItem);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerNotify.removeCallbacks(runnableNotify);
        handlerBullet.removeCallbacks(runnableBullet);
        unregisterReceiver(mapReceiver);
        unregisterReceiver(playerReceiver);
        unregisterReceiver(enemyReceiver);
        service.onStop();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mapView = (MapView) findViewById(R.id.mvMap);
        playerView = (PlayerView) findViewById(R.id.pvPlayer);
        enemyView = (EnemyView) findViewById(R.id.evEnemy);
        bulletPlayerView = (BulletPlayerView) findViewById(R.id.bpvPlayer);
        bulletEnemyView = (BulletEnemyView) findViewById(R.id.bevEnemy);
        mTvLife = (TextView) findViewById(R.id.tvLife);
        mTvShield = (TextView) findViewById(R.id.tvShield);
        mTvLaser = (TextView) findViewById(R.id.tvLaser);
        mTvMap = (TextView) findViewById(R.id.tvMap);
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
        service = app.getGameService();
        mapReceiver = new MapReceiver();
        playerReceiver = new PlayerReceiver();
        enemyReceiver = new EnemyReceiver();
        IntentFilter mapFilter = new IntentFilter(Constant.Game.Type.MAP);
        registerReceiver(mapReceiver, mapFilter);
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
                    service.shotEnemy();
                }
                handlerBullet.postDelayed(runnableBullet, 300);
            }
        };
    }

    /**
     * 开始游戏
     *
     * @param mapItem 地图
     */
    private void startGame(final MapItem mapItem) {
        mTvLife.setText("" + playerData.getLife().getValue());
        mTvShield.setText("CD: " + playerData.getShield().getValue());
        mTvLaser.setText("CD: " + playerData.getLaser().getValue());
        playerView.initLaser(playerData.getRange().getValue());
        if (mapItem != null) {
            mTvMap.setText(mapItem.getMapName());
            handlerNotify.postDelayed(new Runnable() {
                @Override
                public void run() {
                    service.onPrepare(mapItem);
                }
            }, 1000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civShield:
                service.openShield();
                break;
            case R.id.civLaser:
                service.launchLaser();
                break;
            default:
                break;
        }
    }

    //地图接收器
    public class MapReceiver extends BroadcastReceiver {
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
                case Constant.Game.Player.COORD:
                    float x = intent.getFloatExtra(Constant.Game.Player.X, 0);
                    float y = intent.getFloatExtra(Constant.Game.Player.Y, 0);
                    playerView.setLocation(x, y);
                    break;
                case Constant.Game.Player.BULLET:
                    List<Bullet> bullets = (List<Bullet>) intent.getSerializableExtra(Constant.Game.Player.BULLET);
                    bulletPlayerView.setBullets(bullets);
                    break;
                case Constant.Game.Player.SHIELD_OPEN:
                    int coldOpen = intent.getIntExtra(Constant.Game.Player.SHIELD_OPEN, 0);
                    playerView.setShield(true);
                    mTvShield.setText("Wait: " + coldOpen);
                    break;
                case Constant.Game.Player.SHIELD_CLOSE:
                    int coldClose = intent.getIntExtra(Constant.Game.Player.SHIELD_CLOSE, 0);
                    playerView.setShield(false);
                    if (coldClose == playerData.getShield().getValue()) {
                        mTvShield.setText("CD: " + playerData.getShield().getValue());
                    } else {
                        mTvShield.setText("Wait: " + coldClose);
                    }
                    break;
                case Constant.Game.Player.LASER_START:
                    int coldStart = intent.getIntExtra(Constant.Game.Player.LASER_START, 0);
                    playerView.setLaser(true);
                    mTvLaser.setText("Wait: " + coldStart);
                    break;
                case Constant.Game.Player.LASER_STOP:
                    int coldStop = intent.getIntExtra(Constant.Game.Player.LASER_STOP, 0);
                    playerView.setLaser(false);
                    if (coldStop == playerData.getLaser().getValue()) {
                        mTvLaser.setText("CD: " + playerData.getLaser().getValue());
                    } else {
                        mTvLaser.setText("Wait: " + coldStop);
                    }
                    break;
                case Constant.Game.Player.DESTROY:
                    playerView.destroy();
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
                    bulletEnemyView.setBullets(bullets);
                    break;
                default:
                    break;
            }
        }
    }
}
