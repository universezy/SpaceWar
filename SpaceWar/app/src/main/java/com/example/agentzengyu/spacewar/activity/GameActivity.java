package com.example.agentzengyu.spacewar.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.single.MapItem;
import com.example.agentzengyu.spacewar.view.CircleImageView;
import com.example.agentzengyu.spacewar.view.EnemyView;
import com.example.agentzengyu.spacewar.view.MapView;
import com.example.agentzengyu.spacewar.view.PlayerView;

/**
 * 游戏主界面
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getName();

    private MapView mapView;
    private PlayerView playerView;
    private EnemyView enemyView;
    private TextView mTvLife, mTvShield, mTvBomb, mTvMap;
    private CircleImageView mCivShield, mCivBomb, mCivShot;

    private SpaceWarApp app = null;
    private GameReceiver receiver;

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
        unregisterReceiver(receiver);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mapView = (MapView) findViewById(R.id.mvMap);
        playerView = (PlayerView) findViewById(R.id.pvPlayer);
        enemyView = (EnemyView) findViewById(R.id.evEnemy);
        mTvLife = (TextView) findViewById(R.id.tvLife);
        mTvShield = (TextView) findViewById(R.id.tvShield);
        mTvBomb = (TextView) findViewById(R.id.tvBomb);
        mTvMap = (TextView) findViewById(R.id.tvMap);
        mCivShield = (CircleImageView) findViewById(R.id.civShield);
        mCivShield.setOnClickListener(this);
        mCivBomb = (CircleImageView) findViewById(R.id.civBomb);
        mCivBomb.setOnClickListener(this);
        mCivShot = (CircleImageView) findViewById(R.id.civShot);
        mCivShot.setOnClickListener(this);
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        receiver = new GameReceiver();
        IntentFilter filter = new IntentFilter(Constant.BroadCast.GAME);
        registerReceiver(receiver, filter);
    }

    /**
     * 开始游戏
     *
     * @param mapItem 地图
     */
    private void startGame(MapItem mapItem) {
        mTvLife.setText("" + app.getPlayerData().getLife().getValue());
        mTvShield.setText("" + app.getPlayerData().getShield().getValue());
        mTvBomb.setText("" + app.getPlayerData().getBomb().getValue());
        mTvMap.setText(mapItem.getName());
        app.getGameService().startGame(mapItem);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civShield:

                break;
            case R.id.civBomb:

                break;
            case R.id.civShot:

                break;
            default:
                break;
        }
    }

    //游戏广播接收器
    public class GameReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(Constant.BroadCast.STATE);
            Log.e(TAG, state);
            switch (state) {

            }
        }
    }
}
