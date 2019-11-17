package com.zengyu.spacewar.game.controller;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.zengyu.spacewar.game.manager.RuntimeManager;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

public class SkillHandler implements ISkill {
    private static final String TAG = "SkillHandler";

    private static final int COLD_SHIELD = 60;
    private static final int COLD_LASER = 45;
    private static final int COLD_NUCLEAR = 5; // 75
    private static final int DURATION_SHIELD = 5;
    private static final int DURATION_LASER = 5;
    private static final long PERIOD_SHOT = 100L;
    private static final float ALPHA_DISABLE = 0.2f;
    private static final float ALPHA_ENABLE = 0.6f;

    private IProcess mProcess;
    private View mBullet;
    private View mNuclear;
    private View mLaser;
    private View mShield;

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Timer mTimer = new Timer();
    private final Supplier<TimerTask> mShotSupplier = () ->
            new TimerTask() {
                @Override
                public void run() {
                    RuntimeManager.getInstance().createPlayerBullet();
                }
            };
    private TimerTask mShotTask;

    public SkillHandler(IProcess iProcess, View vBullet, View vNuclear, View vLaser, View vShield) {
        mProcess = iProcess;
        mBullet = vBullet;
        mNuclear = vNuclear;
        mLaser = vLaser;
        mShield = vShield;
    }

    public void destroy() {
        mHandler.removeCallbacksAndMessages(null);
        Optional.ofNullable(mShotTask).ifPresent(TimerTask::cancel);
        mTimer.cancel();
    }

    @Override
    public void onBullet(boolean shot) {
        if (mProcess.getProcessState() != IProcess.State.RUNNING) return;
        if (shot) {
            mBullet.setAlpha(ALPHA_DISABLE);
            mShotTask = mShotSupplier.get();
            mTimer.scheduleAtFixedRate(mShotTask, 0, PERIOD_SHOT);
        } else {
            mBullet.setAlpha(ALPHA_ENABLE);
            mShotTask.cancel();
        }
    }

    @Override
    public void onShield() {
        if (mProcess.getProcessState() != IProcess.State.RUNNING) return;
        scheduleView(mShield, COLD_SHIELD);
        RuntimeManager.getInstance().getPlayer().setShieldState(true);
        resetSkill(() -> RuntimeManager.getInstance().getPlayer().setShieldState(false), DURATION_SHIELD);
    }

    @Override
    public void onLaser() {
        if (mProcess.getProcessState() != IProcess.State.RUNNING) return;
        scheduleView(mLaser, COLD_LASER);
        RuntimeManager.getInstance().getPlayer().setLaserState(true);
        resetSkill(() -> RuntimeManager.getInstance().getPlayer().setLaserState(false), DURATION_LASER);
    }

    @Override
    public void onNuclear() {
        if (mProcess.getProcessState() != IProcess.State.RUNNING) return;
        scheduleView(mNuclear, COLD_NUCLEAR);
        RuntimeManager.getInstance().createNuclear();
    }

    private void scheduleView(View v, int cold) {
        if (v == null) return;
        v.setEnabled(false);
        v.setAlpha(ALPHA_DISABLE);
        mHandler.postDelayed(() -> {
            if (v != null) {
                v.setEnabled(true);
                v.setAlpha(ALPHA_ENABLE);
            }
        }, cold * 1000);
    }

    private void resetSkill(Runnable runnable, int duration) {
        mHandler.postDelayed(runnable, duration * 1000);
    }
}
