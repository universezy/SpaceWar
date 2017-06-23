package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Config;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.BasicData;
import com.example.agentzengyu.spacewar.util.BasicDataHandler;
import com.example.agentzengyu.spacewar.util.DataHandlerCallBack;
import com.example.agentzengyu.spacewar.util.PlayerDataHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 太空大战服务
 */
public class SpaceWarService extends Service {
    private SpaceWarApp app = null;
    private BasicData data = new BasicData();

    @Override
    public void onCreate() {
        super.onCreate();
        app = (SpaceWarApp) getApplication();
        app.setService(this);
        initBasicData();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化基础数据
     */
    public void initBasicData() {
        BasicDataHandler handler = new BasicDataHandler();
        try {
            InputStream inputStream = getResources().getAssets().open(Config.FILE_SHOP);
            handler.setResource(data, inputStream).read(new DataHandlerCallBack() {
                @Override
                public void onStart(String s) {
                    Log.e("onStart", s);
                }

                @Override
                public void onSuccess(String s) {
                    Log.e("onSuccess", s);
                    initPlayerData();
                }

                @Override
                public void onFailure(String s, Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化玩家数据
     */
    public void initPlayerData() {
        PlayerDataHandler handler = new PlayerDataHandler();
        File file = new File(getFilesDir(), Config.FILE_PLAYER);
        if (!file.exists()) {
            try {
                file.createNewFile();
                app.getPlayerData().setLife(data.getLives().get(0));
                app.getPlayerData().setDefense(data.getDefenses().get(0));
                app.getPlayerData().setAgility(data.getAgilities().get(0));
                app.getPlayerData().setShield(data.getShields().get(0));
                app.getPlayerData().setPower(data.getPowers().get(0));
                app.getPlayerData().setSpeed(data.getSpeeds().get(0));
                app.getPlayerData().setRange(data.getRanges().get(0));
                app.getPlayerData().setBomb(data.getBombs().get(0));
                savePlayerData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } else {
            handler.setResource(app.getPlayerData(), file).read(new DataHandlerCallBack() {
                @Override
                public void onStart(String s) {
                    Log.e("onStart", s);
                }

                @Override
                public void onSuccess(String s) {
                    Log.e("onSuccess", s);
                }

                @Override
                public void onFailure(String s, Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 保存玩家数据
     */
    public void savePlayerData() {
        PlayerDataHandler handler = new PlayerDataHandler();
        File file = new File(getFilesDir(), Config.FILE_PLAYER);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        handler.setResource(app.getPlayerData(), file).save(new DataHandlerCallBack() {
            @Override
            public void onStart(String s) {
                Log.e("onStart", s);
            }

            @Override
            public void onSuccess(String s) {
                Log.e("onSuccess", s);
            }

            @Override
            public void onFailure(String s, Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 获得基础数据
     *
     * @return
     */
    public BasicData getData() {
        return data;
    }

    /**
     * 服务绑定类
     */
    public class ServiceBinder extends Binder {
    }
}
