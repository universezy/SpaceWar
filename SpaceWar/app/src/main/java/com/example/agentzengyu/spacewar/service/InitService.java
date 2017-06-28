package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.handler.PlayerHandler;
import com.example.agentzengyu.spacewar.handler.ShopHandler;
import com.example.agentzengyu.spacewar.others.HandlerCallBack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 太空大战服务
 */
public class InitService extends Service {
    private SpaceWarApp app = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("InitService", "onCreate.");
        app = (SpaceWarApp) getApplication();
        app.setInitService(this);
        initShopData();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e("InitService", "onStart.");
        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e("InitService", "onDestroy.");
        super.onDestroy();
    }

    /**
     * 初始化商店数据
     */
    public void initShopData() {
        try {
            InputStream inputStream = getResources().getAssets().open(Constant.FileName.SHOP);
            ShopHandler handler = new ShopHandler(app.getShopData(), null, inputStream);
            handler.read(new HandlerCallBack() {
                @Override
                public void onStart(String message) {
                    Log.e("onStart", message);
                }

                @Override
                public void onProcess(int progress) {
                    Log.e("onProcess", "" + progress);
                    Intent intentBasic = new Intent(Constant.BroadCast.LOADING);
                    intentBasic.putExtra(Constant.BroadCast.STATE, Constant.Status.PROGRESS);
                    intentBasic.putExtra(Constant.Status.PROGRESS, progress * 10);
                    sendBroadcast(intentBasic);
                }

                @Override
                public void onSuccess(String result) {
                    Log.e("onSuccess", result);
                    Intent intentBasic = new Intent(Constant.BroadCast.LOADING);
                    intentBasic.putExtra(Constant.BroadCast.STATE, Constant.Status.SHOP);
                    intentBasic.putExtra(Constant.Status.PROGRESS, 100);
                    sendBroadcast(intentBasic);
                    initPlayerData();
                }

                @Override
                public void onFailure(String result, Exception e) {
                    Log.e("onFailure", result);
                    if (Constant.Status.DESTROY.equals(result)) {
                        Intent intentBasic = new Intent(Constant.BroadCast.LOADING);
                        intentBasic.putExtra(Constant.BroadCast.STATE, Constant.Status.SHOP);
                        intentBasic.putExtra(Constant.Status.PROGRESS, -1);
                        sendBroadcast(intentBasic);
                    }
                    if (e != null)
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
        final File file = new File(getFilesDir(), Constant.FileName.PLAYER);
        PlayerHandler handler = new PlayerHandler(app.getPlayerData(), file, null);
        if (!file.exists()) {
            try {
                file.createNewFile();
                handler.init(new HandlerCallBack() {
                    @Override
                    public void onStart(String message) {
                        Log.e("onStart", message);
                    }

                    @Override
                    public void onProcess(int progress) {
                        Log.e("onProcess", "" + progress);
                        Intent intentPlayer = new Intent(Constant.BroadCast.LOADING);
                        intentPlayer.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                        intentPlayer.putExtra(Constant.Status.PROGRESS, progress * 10);
                        sendBroadcast(intentPlayer);
                    }

                    @Override
                    public void onSuccess(String result) {
                        Log.e("onSuccess", result);
                        Intent intentPlayer = new Intent(Constant.BroadCast.LOADING);
                        intentPlayer.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                        intentPlayer.putExtra(Constant.Status.PROGRESS, 100);
                        sendBroadcast(intentPlayer);
                        savePlayerData(true);
                    }

                    @Override
                    public void onFailure(String result, Exception e) {
                        Log.e("onFailure", result);
                        if (Constant.Status.DESTROY.equals(result)) {
                            Intent intentPlayer = new Intent(Constant.BroadCast.LOADING);
                            intentPlayer.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                            intentPlayer.putExtra(Constant.Status.PROGRESS, -1);
                            sendBroadcast(intentPlayer);
                            file.delete();
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            initPlayerData();
                        }
                        if (e != null)
                            e.printStackTrace();
                    }
                }, app.getShopData());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } else {
            handler.read(new HandlerCallBack() {
                @Override
                public void onStart(String message) {
                    Log.e("onStart", message);
                }

                @Override
                public void onProcess(int progress) {
                    Log.e("onProcess", "" + progress);
                    Intent intentPlayer = new Intent(Constant.BroadCast.LOADING);
                    intentPlayer.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                    intentPlayer.putExtra(Constant.Status.PROGRESS, progress * 10);
                    sendBroadcast(intentPlayer);
                }

                @Override
                public void onSuccess(String result) {
                    Log.e("onSuccess", result);
                    Intent intentPlayer = new Intent(Constant.BroadCast.LOADING);
                    intentPlayer.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                    intentPlayer.putExtra(Constant.Status.PROGRESS, 100);
                    sendBroadcast(intentPlayer);
                }

                @Override
                public void onFailure(String result, Exception e) {
                    Log.e("onFailure", result);
                    if (Constant.Status.DESTROY.equals(result)) {
                        Intent intentPlayer = new Intent(Constant.BroadCast.LOADING);
                        intentPlayer.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                        intentPlayer.putExtra(Constant.Status.PROGRESS, -1);
                        sendBroadcast(intentPlayer);
                        file.delete();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        initPlayerData();
                    }
                    if (e != null)
                        e.printStackTrace();
                }
            });
        }
    }

    /**
     * 保存玩家数据
     */
    public void savePlayerData(final boolean isInitial) {
        File file = new File(getFilesDir(), Constant.FileName.PLAYER);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        PlayerHandler handler = new PlayerHandler(app.getPlayerData(), file, null);
        handler.save(new HandlerCallBack() {
            @Override
            public void onStart(String message) {
                Log.e("onStart", message);
            }

            @Override
            public void onProcess(int progress) {
                Log.e("progress", "" + progress);
            }

            @Override
            public void onSuccess(String result) {
                Log.e("onSuccess", result);
                if (isInitial) {
                    initMapData();
                }
            }

            @Override
            public void onFailure(String result, Exception e) {
                Log.e("onFailure", result);
                if (e != null)
                    e.printStackTrace();
            }
        });
    }

    /**
     * 初始化地图数据
     */
    public void initMapData() {

    }

    /**
     * 初始化敌人数据
     */
    public void initEnemyData() {

    }
}
