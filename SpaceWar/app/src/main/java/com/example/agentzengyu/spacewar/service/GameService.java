package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.engine.IEngine;
import com.example.agentzengyu.spacewar.engine.SpaceWarEngine;
import com.example.agentzengyu.spacewar.entity.single.Bullet;
import com.example.agentzengyu.spacewar.entity.single.MapItem;

import java.util.ArrayList;

/**
 * 游戏服务
 */
public class GameService extends Service implements IEngine {
    private final String TAG = getClass().getName();
    private SpaceWarApp app = null;
    private SpaceWarEngine engine = null;

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate.");
        super.onCreate();
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
        stopGame();
        engine = null;
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        Log.e(TAG, "initVariable.");
        app = (SpaceWarApp) getApplication();
        app.setGameService(this);
    }

    /**
     * 初始化游戏引擎
     */
    private void initEngine() {
        Log.e(TAG, "initEngine.");
        if (engine == null) {
            engine = SpaceWarEngine.getInstance(app.getApplicationContext());
        }
        engine.initMsgCallBack(this);
    }

    /**
     * 启动游戏
     */
    public void startGame(MapItem mapItem) {
        Log.e(TAG, "startGame.");
        initMap();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        engine.prepare(app.getPlayerData(), mapItem);
    }

    /**
     * 暂停游戏
     */
    public void pauseGame() {
        engine.onPause();
    }

    /**
     * 继续游戏
     */
    public void continueGame() {
        engine.onContinue();
    }

    /**
     * 停止游戏
     */
    public void stopGame() {
        engine.onStop();
    }

    public void shotEnemy(float x, float y) {
        engine.shotEnemy(x, y);
    }

    @Override
    public void notifyInitMsg(String message, boolean status) {
        Log.e(TAG, message);
        Intent intent = new Intent(Constant.Game.Type.PLAYER);
        intent.putExtra(Constant.BroadCast.STATE, Constant.Game.Type.NOTIFY);
        intent.putExtra(Constant.Game.Type.NOTIFY, message);
        intent.putExtra(Constant.Game.Type.STATUS, status);
        sendBroadcast(intent);
    }

    @Override
    public void notifyProgressMsg(String message) {
        Log.e(TAG, message);
    }

    @Override
    public void initMap() {
        Log.e(TAG, "initMap.");

    }

    @Override
    public void initEnemy(ArrayList<Bullet> bulletsEnemy) {
        Log.e(TAG, "initEnemy.");
        Intent intent = new Intent(Constant.Game.Type.ENEMY);
        intent.putExtra(Constant.BroadCast.STATE, Constant.Game.Type.BULLET);
        intent.putExtra(Constant.Game.Type.BULLET, bulletsEnemy);
        sendBroadcast(intent);
    }

    @Override
    public void initPlayer(ArrayList<Bullet> bulletsPlayer) {
        Log.e(TAG, "initPlayer.");
        Intent intent = new Intent(Constant.Game.Type.PLAYER);
        intent.putExtra(Constant.BroadCast.STATE, Constant.Game.Type.BULLET);
        intent.putExtra(Constant.Game.Type.BULLET, bulletsPlayer);
        sendBroadcast(intent);
    }

    @Override
    public void updateMap() {
        Log.e(TAG, "updateMap.");
        Intent intent = new Intent(Constant.Game.Type.MAP);
        intent.putExtra(Constant.BroadCast.STATE, Constant.Game.Type.MAP);
        //TODO
//        sendBroadcast(intent);
    }

    @Override
    public void updateEnemy() {
        Log.e(TAG, "updateEnemy.");
        Intent intent = new Intent(Constant.Game.Type.ENEMY);
        intent.putExtra(Constant.BroadCast.STATE, Constant.Game.Type.ENEMY);
        //TODO
//        sendBroadcast(intent);
    }

    @Override
    public void updatePlayer(String directionX, String directionY, String shieldStatus, boolean destroy) {
        Log.e(TAG, "updatePlayer.");
        if (Constant.Game.Player.SHIELD_OPEN.equals(shieldStatus) ||
                Constant.Game.Player.SHIELD_CLOSE.equals(shieldStatus)) {
            Intent intent = new Intent(Constant.Game.Type.PLAYER);
            intent.putExtra(Constant.BroadCast.STATE, shieldStatus);
            sendBroadcast(intent);
        } else if (destroy) {
            Intent intent = new Intent(Constant.Game.Type.PLAYER);
            intent.putExtra(Constant.BroadCast.STATE, Constant.Game.Player.DESTROY);
            sendBroadcast(intent);
        }
        if (Constant.Game.Player.LEFT.equals(directionY) ||
                Constant.Game.Player.RIGHT.equals(directionY)) {
            Intent intent = new Intent(Constant.Game.Type.PLAYER);
            intent.putExtra(Constant.BroadCast.STATE, directionY);
            sendBroadcast(intent);
        }
        if (Constant.Game.Player.TOP.equals(directionX) ||
                Constant.Game.Player.BOTTOM.equals(directionX)) {
            Intent intent = new Intent(Constant.Game.Type.PLAYER);
            intent.putExtra(Constant.BroadCast.STATE, directionX);
            sendBroadcast(intent);
        }
    }
}
