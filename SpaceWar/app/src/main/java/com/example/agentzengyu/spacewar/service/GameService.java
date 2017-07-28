package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.engine.IMessage;
import com.example.agentzengyu.spacewar.engine.IPlayer;
import com.example.agentzengyu.spacewar.engine.IStatus;
import com.example.agentzengyu.spacewar.engine.SpaceWarEngine;
import com.example.agentzengyu.spacewar.entity.basic.single.Level;
import com.example.agentzengyu.spacewar.view.GameSurfaceView;

/**
 * 游戏服务，用于转发UI和引擎之间的消息
 */
public class GameService extends Service implements IStatus, IPlayer, IMessage {
    private final String TAG = getClass().getName();
    private SpaceWarApp app = null;
    private SpaceWarEngine engine = null;

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();
        initVariable();
        initEngine();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG, "onStart");
        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        onStop();
        engine = null;
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        Log.e(TAG, "initVariable");
        app = (SpaceWarApp) getApplication();
        app.setGameGameService(this);
    }

    /**
     * 初始化游戏引擎
     */
    private void initEngine() {
        Log.e(TAG, "initEngine");
        if (engine == null) {
            engine = SpaceWarEngine.getInstance(app.getApplicationContext());
        }
        engine.initEngineCallBack(this);
    }

    /********************************* IStatus *********************************/
    @Override
    public void onPrepare(Level level, GameSurfaceView gameSurfaceView) {
        engine.onPrepare(level, gameSurfaceView);
    }

    @Override
    public void onStart() {
        engine.onStart();
    }

    @Override
    public void onPause() {
        engine.onPause();
    }

    @Override
    public void onContinue() {
        engine.onContinue();
    }

    @Override
    public void onStop() {
        engine.onStop();
    }

    /********************************* IPlayer *********************************/
    @Override
    public void shotEnemy() {
        engine.shotEnemy();
    }

    @Override
    public void openShield() {
        engine.openShield();
    }

    @Override
    public void launchLaser() {
        engine.launchLaser();
    }

    /********************************* IMessage *********************************/
    @Override
    public void notifyInitMsg(String message, boolean status) {
        Log.e(TAG, message);
        Intent intent = new Intent(Constant.Game.Type.PLAYER);
        intent.putExtra(Constant.BroadCast.TARGET, Constant.Game.Type.NOTIFY);
        intent.putExtra(Constant.Game.Type.NOTIFY, message);
        intent.putExtra(Constant.Game.Type.STATUS, status);
        sendBroadcast(intent);
    }

    @Override
    public void notifyGameMsg(String message) {
        Log.e(TAG, message);
    }
}
