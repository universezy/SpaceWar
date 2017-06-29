package com.example.agentzengyu.spacewar.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        initView();
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

    public class GameReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
