package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.engine.IEngine;
import com.example.agentzengyu.spacewar.engine.SpaceWarEngine;
import com.example.agentzengyu.spacewar.entity.single.MapItem;

/**
 * 游戏服务
 */
public class GameService extends Service implements IEngine {
    private final String TAG = getClass().getName();
    private SpaceWarApp app = null;
    private SpaceWarEngine engine = null;
    private Handler mapHandler = new Handler();
    private Runnable mapRunnable = null;
    private boolean mapRunning = true;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate.");
        initVariable();
        initEngine();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG, "onStart.");
        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy.");
        super.onDestroy();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        app = (SpaceWarApp) getApplication();
        app.setGameService(this);
        mapRunnable = new Runnable() {
            @Override
            public void run() {
                if (mapRunning) {
                    

                    Intent intent = new Intent(Constant.BroadCast.GAME);
                    intent.putExtra(Constant.BroadCast.STATE,Constant.UpdateView.MAP);

                    mapHandler.postDelayed(mapRunnable,100);
                }
            }
        };
    }

    /**
     * 初始化游戏引擎
     */
    private void initEngine() {
        if (engine == null) {
            engine = SpaceWarEngine.getInstance();
        }
        engine.initMsgCallBack(this);
    }

    /**
     * 启动游戏
     */
    public void startGame(MapItem mapItem) {
        engine.loadMirror(app.getPlayerData(), mapItem.getEnemys());
        mapHandler.postDelayed(mapRunnable, 100);
        engine.onStart();
    }

    /**
     * 暂停游戏
     */
    public void pauseGame() {
        mapRunning = false;
        engine.onPause();
    }

    /**
     * 继续游戏
     */
    public void continueGame() {
        mapRunning = true;
        engine.onContinue();
    }

    /**
     * 停止游戏
     */
    public void stopGame() {
        mapRunning = false;
        mapHandler.removeCallbacks(mapRunnable);
        engine.onStop();
    }

    @Override
    public void notifyInitMsg(String message) {
        Log.e(TAG, message);
    }

    @Override
    public void notifyProgressMsg(String message) {
        Log.e(TAG, message);
    }

    @Override
    public void updateEnemyLocation() {

    }

    @Override
    public void updatePlayerLocation() {

    }
}