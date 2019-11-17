package com.zengyu.spacewar.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.zengyu.spacewar.R;
import com.zengyu.spacewar.game.controller.SkillHandler;
import com.zengyu.spacewar.game.controller.IProcess;
import com.zengyu.spacewar.game.manager.BitmapManager;
import com.zengyu.spacewar.view.widget.GameSurfaceView;

public class GameActivity extends AppCompatActivity {

    private GameSurfaceView mSvGame;
    private ImageButton mIbNuclear;
    private ImageButton mIbLaser;
    private ImageButton mIbShield;
    private ImageButton mIbBullet;

    private SkillHandler mAttackHandler;
    private IProcess mProcess;
    private View.OnTouchListener mBulletListener = (v, event) -> {
        if (mAttackHandler == null) return false;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mAttackHandler.onBullet(true);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mAttackHandler.onBullet(false);
            default:
                return false;
        }
        return true;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initView();
        initComponent();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mProcess != null) {
            mProcess.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyComponent();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mProcess != null) {
            mProcess.stop();
        }
    }

    private void initView() {
        mSvGame = findViewById(R.id.svGame);
        mProcess = mSvGame;
        mIbNuclear = findViewById(R.id.ibNuclear);
        mIbLaser = findViewById(R.id.ibLaser);
        mIbShield = findViewById(R.id.ibShield);
        mIbBullet = findViewById(R.id.ibBullet);
    }

    private void initComponent() {
        mAttackHandler = new SkillHandler(mProcess, mIbBullet, mIbNuclear, mIbLaser, mIbShield);
        mIbBullet.setOnTouchListener(mBulletListener);
        mIbShield.setOnClickListener(view -> mAttackHandler.onShield());
        mIbLaser.setOnClickListener(view -> mAttackHandler.onLaser());
        mIbNuclear.setOnClickListener(view -> mAttackHandler.onNuclear());
        BitmapManager.getInstance().init(this);
    }

    private void destroyComponent() {
        BitmapManager.getInstance().destroy();
        if (mAttackHandler != null) {
            mAttackHandler.destroy();
        }
    }
}
