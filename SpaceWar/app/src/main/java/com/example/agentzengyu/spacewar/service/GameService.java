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
import com.example.agentzengyu.spacewar.entity.single.MapItem;

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
    public void onPrepare(MapItem mapItem) {
        engine.onPrepare(mapItem);
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
    public void launchBomb() {
        engine.launchBomb();
    }

    /********************************* IMessageCallback *********************************/
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

    /********************************* IEventCallback *********************************/
    @Override
    public void updateMap() {
        Log.e(TAG, "updateMap");
        Intent intent = new Intent(Constant.Game.Type.MAP);
        intent.putExtra(Constant.BroadCast.STATE, Constant.Game.Type.MAP);
        //TODO
//        sendBroadcast(intent);
    }

    @Override
    public void updateEnemy() {
        Log.e(TAG, "updateEnemy");
        Intent intent = new Intent(Constant.Game.Type.ENEMY);
        intent.putExtra(Constant.BroadCast.STATE, Constant.Game.Type.ENEMY);
        //TODO
//        sendBroadcast(intent);
    }

    @Override
    public void updatePlayer(float x, float y) {
        Log.e(TAG, "updatePlayer");
        Intent intent = new Intent(Constant.Game.Type.PLAYER);
        intent.putExtra(Constant.BroadCast.STATE, Constant.Game.Player.LOCATION);
        intent.putExtra(Constant.Game.Player.X, x);
        intent.putExtra(Constant.Game.Player.Y, y);
        sendBroadcast(intent);
    }

    @Override
    public void setShield(boolean open) {
        Log.e(TAG, "setShield");
    }

    @Override
    public void updateBomb(float x, float y) {
        Log.e(TAG, "updateBomb");
    }

    @Override
    public void destroyPlayer() {
        Log.e(TAG, "destroyPlayer");
    }
}
