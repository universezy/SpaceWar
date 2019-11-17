package com.zengyu.spacewar.game.controller;

import com.zengyu.spacewar.game.manager.RuntimeManager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

public class Scheduler implements IScheduler {
    private static final String TAG = "Scheduler";
    private static final long PERIOD_ENEMY = 2 * 1000;
    private static final long PERIOD_BLOCK = 8 * 1000;
    private static final long DELAY_ENEMY = 3 * 1000;
    private static final long DELAY_BLOCK = 10 * 1000;
    private Timer mTimer = new Timer();
    private final Supplier<TimerTask> mEnemySupplier = () ->
            new TimerTask() {
                @Override
                public void run() {
                    RuntimeManager.getInstance().createEnemy();
                }
            };
    private final Supplier<TimerTask> mBlockSupplier = () ->
            new TimerTask() {
                @Override
                public void run() {
                    RuntimeManager.getInstance().createBlock();
                }
            };

    @Override
    public void start() {
        mTimer.scheduleAtFixedRate(mEnemySupplier.get(), DELAY_ENEMY, PERIOD_ENEMY);
        mTimer.scheduleAtFixedRate(mBlockSupplier.get(), DELAY_BLOCK, PERIOD_BLOCK);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void stop() {
        mTimer.cancel();
    }
}
