package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.engine.IEventCallback;
import com.example.agentzengyu.spacewar.engine.IEventHandle;
import com.example.agentzengyu.spacewar.engine.IMessageCallback;
import com.example.agentzengyu.spacewar.engine.IStatusHandle;
import com.example.agentzengyu.spacewar.engine.SpaceWarEngine;
import com.example.agentzengyu.spacewar.entity.single.Bullet;
import com.example.agentzengyu.spacewar.entity.single.Level;

import java.io.Serializable;
import java.util.List;

/**
 * 游戏服务，用于转发UI和引擎之间的消息
 */
public class GameService extends Service implements IStatusHandle, IEventHandle, IMessageCallback, IEventCallback {
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
        app.setGameService(this);
    }

    /**
     * 初始化游戏引擎
     */
    private void initEngine() {
        Log.e(TAG, "initEngine");
        if (engine == null) {
            engine = SpaceWarEngine.getInstance(app.getApplicationContext());
        }
        engine.initEngineCallBack(this, this);
    }

    /********************************* IStatusHandle *********************************/
    @Override
    public void onPrepare(Level level) {
        engine.onPrepare(level);
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

    /********************************* IEventHandle *********************************/
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

    /********************************* IMessageCallback *********************************/
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
    public void notifyProgressMsg(String message) {
        Log.e(TAG, message);
    }

    /********************************* IEventCallback *********************************/
    @Override
    public void updateLevelLocation() {
        Log.e(TAG, "updateMap");
        Intent intent = new Intent(Constant.Game.Type.LEVEL);
        intent.putExtra(Constant.BroadCast.TARGET, Constant.Game.Type.LEVEL);
        //TODO
//        sendBroadcast(intent);
    }

    @Override
    public void updateEnemyLocation() {
        Log.e(TAG, "updateEnemy");
        Intent intent = new Intent(Constant.Game.Type.ENEMY);
        intent.putExtra(Constant.BroadCast.TARGET, Constant.Game.Type.ENEMY);
        //TODO
//        sendBroadcast(intent);
    }

    @Override
    public void updateEnemyBullets(List<Bullet> bullets) {
//        Log.e(TAG, "updateEnemyBullets");
        Intent intent = new Intent(Constant.Game.Type.ENEMY);
        intent.putExtra(Constant.BroadCast.TARGET, Constant.Game.Enemy.BULLET);
        intent.putExtra(Constant.Game.Enemy.BULLET, (Serializable) bullets);
        sendBroadcast(intent);
    }

    @Override
    public void updatePlayerLocation(float x, float y) {
        Intent intent = new Intent(Constant.Game.Type.PLAYER);
        intent.putExtra(Constant.BroadCast.TARGET, Constant.Game.Player.COORD);
        intent.putExtra(Constant.Game.Player.X, x);
        intent.putExtra(Constant.Game.Player.Y, y);
        sendBroadcast(intent);
    }

    @Override
    public void updatePlayerBullets(List<Bullet> bullets) {
        Intent intent = new Intent(Constant.Game.Type.PLAYER);
        intent.putExtra(Constant.BroadCast.TARGET, Constant.Game.Player.BULLET);
        intent.putExtra(Constant.Game.Player.BULLET, (Serializable) bullets);
        sendBroadcast(intent);
    }

    @Override
    public void setShield(boolean open,int cold) {
        Intent intent = new Intent(Constant.Game.Type.PLAYER);
        if (open) {
            intent.putExtra(Constant.BroadCast.TARGET, Constant.Game.Player.SHIELD_OPEN);
            intent.putExtra(Constant.Game.Player.SHIELD_OPEN, cold);
        } else {
            intent.putExtra(Constant.BroadCast.TARGET, Constant.Game.Player.SHIELD_CLOSE);
            intent.putExtra(Constant.Game.Player.SHIELD_CLOSE, cold);
        }
        sendBroadcast(intent);
    }

    @Override
    public void setLaser(boolean start, int cold) {
        Intent intent = new Intent(Constant.Game.Type.PLAYER);
        if (start) {
            intent.putExtra(Constant.BroadCast.TARGET, Constant.Game.Player.LASER_START);
            intent.putExtra(Constant.Game.Player.LASER_START, cold);
        } else {
            intent.putExtra(Constant.BroadCast.TARGET, Constant.Game.Player.LASER_STOP);
            intent.putExtra(Constant.Game.Player.LASER_STOP, cold);
        }
        sendBroadcast(intent);
    }

    @Override
    public void destroyPlayer() {
        Log.e(TAG, "destroyPlayer");
    }
}
