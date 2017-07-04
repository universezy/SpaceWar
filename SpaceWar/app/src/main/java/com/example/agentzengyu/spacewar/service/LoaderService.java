package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.loader.ILoaderCallback;
import com.example.agentzengyu.spacewar.loader.PlayerLoader;
import com.example.agentzengyu.spacewar.loader.ShopLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 初始化服务
 */
public class LoaderService extends Service {
    private final String TAG = getClass().getName();
    private SpaceWarApp app = null;

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate.");
        super.onCreate();
        app = (SpaceWarApp) getApplication();
        app.setLoaderService(this);
        initShopData();
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
     * 初始化商店数据
     */
    public void initShopData() {
        try {
            InputStream inputStream = getResources().getAssets().open(Constant.FileName.SHOP);
            ShopLoader handler = new ShopLoader(app.getShopLibrary(), null, inputStream);
            handler.read(new ILoaderCallback() {
                @Override
                public void onStart(String message) {
                    Log.e("onStart", message);
                }

                @Override
                public void onProcess(int progress) {
                    Log.e("onProcess", "" + progress);
                    Intent intent = new Intent(Constant.BroadCast.LOADING);
                    intent.putExtra(Constant.BroadCast.STATE, Constant.Status.SHOP);
                    intent.putExtra(Constant.Status.PROGRESS, progress * 10);
                    sendBroadcast(intent);
                }

                @Override
                public void onSuccess(String result) {
                    Log.e("onSuccess", result);
                    Intent intent = new Intent(Constant.BroadCast.LOADING);
                    intent.putExtra(Constant.BroadCast.STATE, Constant.Status.SHOP);
                    intent.putExtra(Constant.Status.PROGRESS, 100);
                    sendBroadcast(intent);
                    initPlayerData();
                }

                @Override
                public void onFailure(String result, Exception e) {
                    Log.e("onFailure", result);
                    if (Constant.Status.DESTROY.equals(result)) {
                        Intent intent = new Intent(Constant.BroadCast.LOADING);
                        intent.putExtra(Constant.BroadCast.STATE, Constant.Status.SHOP);
                        intent.putExtra(Constant.Status.PROGRESS, -1);
                        sendBroadcast(intent);
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
        PlayerLoader handler = new PlayerLoader(app.getPlayerData(), file, null);
        if (!file.exists()) {
            try {
                file.createNewFile();
                handler.init(new ILoaderCallback() {
                    @Override
                    public void onStart(String message) {
                        Log.e("onStart", message);
                    }

                    @Override
                    public void onProcess(int progress) {
                        Log.e("onProcess", "" + progress);
                        Intent intent = new Intent(Constant.BroadCast.LOADING);
                        intent.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                        intent.putExtra(Constant.Status.PROGRESS, progress * 10);
                        sendBroadcast(intent);
                    }

                    @Override
                    public void onSuccess(String result) {
                        Log.e("onSuccess", result);
                        Intent intent = new Intent(Constant.BroadCast.LOADING);
                        intent.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                        intent.putExtra(Constant.Status.PROGRESS, 100);
                        sendBroadcast(intent);
                        savePlayerData(true);
                    }

                    @Override
                    public void onFailure(String result, Exception e) {
                        Log.e("onFailure", result);
                        if (Constant.Status.DESTROY.equals(result)) {
                            Intent intent = new Intent(Constant.BroadCast.LOADING);
                            intent.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                            intent.putExtra(Constant.Status.PROGRESS, -1);
                            sendBroadcast(intent);
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
                }, app.getShopLibrary());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } else {
            handler.read(new ILoaderCallback() {
                @Override
                public void onStart(String message) {
                    Log.e("onStart", message);
                }

                @Override
                public void onProcess(int progress) {
                    Log.e("onProcess", "" + progress);
                    Intent intent = new Intent(Constant.BroadCast.LOADING);
                    intent.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                    intent.putExtra(Constant.Status.PROGRESS, progress * 10);
                    sendBroadcast(intent);
                }

                @Override
                public void onSuccess(String result) {
                    Log.e("onSuccess", result);
                    Intent intent = new Intent(Constant.BroadCast.LOADING);
                    intent.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                    intent.putExtra(Constant.Status.PROGRESS, 100);
                    sendBroadcast(intent);
                    initMapData();
                }

                @Override
                public void onFailure(String result, Exception e) {
                    Log.e("onFailure", result);
                    if (Constant.Status.DESTROY.equals(result)) {
                        Intent intent = new Intent(Constant.BroadCast.LOADING);
                        intent.putExtra(Constant.BroadCast.STATE, Constant.Status.PLAYER);
                        intent.putExtra(Constant.Status.PROGRESS, -1);
                        sendBroadcast(intent);
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
        PlayerLoader handler = new PlayerLoader(app.getPlayerData(), file, null);
        handler.save(new ILoaderCallback() {
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
