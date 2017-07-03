package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
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
        engine.prepare(app.getPlayerData(), mapItem);
        initMap();
        initEnemy();
        initPlayer();
        engine.onStart();
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

    @Override
    public void notifyInitMsg(String message) {
        Log.e(TAG, message);
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
    public void initEnemy() {
        Log.e(TAG, "initEnemy.");

    }

    @Override
    public void initPlayer() {
        Log.e(TAG, "initPlayer.");
        Intent intent = new Intent(Constant.Game.Type.PLAYER);
        intent.putExtra(Constant.BroadCast.STATE, Constant.Game.Player.AGILITY);
        intent.putExtra(Constant.Game.Player.AGILITY, app.getPlayerData().getAgility());
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
    public void updatePlayer(String direction, String shieldStatus, boolean destroy) {
        Log.e(TAG, "updatePlayer.");
        Intent intent = new Intent(Constant.Game.Type.PLAYER);
        if (Constant.Game.Player.LEFT.equals(direction) ||
                Constant.Game.Player.RIGHT.equals(direction) ||
                Constant.Game.Player.TOP.equals(direction) ||
                Constant.Game.Player.BOTTOM.equals(direction)) {
            intent.putExtra(Constant.BroadCast.STATE, direction);
        } else if (Constant.Game.Player.SHIELD_OPEN.equals(shieldStatus) ||
                Constant.Game.Player.SHIELD_CLOSE.equals(shieldStatus)) {
            intent.putExtra(Constant.BroadCast.STATE, shieldStatus);
        } else if (destroy) {
            intent.putExtra(Constant.BroadCast.STATE, Constant.Game.Player.DESTROY);
        }
        sendBroadcast(intent);
    }
}
