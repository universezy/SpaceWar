package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Config;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.BasicData;
import com.example.agentzengyu.spacewar.handler.BasicHandler;
import com.example.agentzengyu.spacewar.handler.PlayerHandler;
import com.example.agentzengyu.spacewar.others.DataHandlerCallBack;

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
        Log.e("SpaceWarService", "Service has created.");
        app = (SpaceWarApp) getApplication();
        app.setService(this);
        initBasicData();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e("SpaceWarService", "Service has started.");
        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e("SpaceWarService", "Service has destroyed.");
        super.onDestroy();
    }

    /**
     * 初始化基础数据
     */
    public void initBasicData() {
        BasicHandler handler = new BasicHandler();
        try {
            InputStream inputStream = getResources().getAssets().open(Config.FILE_SHOP);
            handler.setResource(data, inputStream).read(new DataHandlerCallBack() {
                @Override
                public void onStart(String message) {
                    Log.e("onStart", message);
                }

                @Override
                public void onProcess(int progress) {
                    Log.e("onProcess", "" + progress);
                    Intent intentBasic = new Intent(Config.STATE_LOADING);
                    intentBasic.putExtra(Config.STATE, Config.STATE_BASIC);
                    intentBasic.putExtra(Config.PROGRESS, progress * 10);
                    sendBroadcast(intentBasic);
                }

                @Override
                public void onSuccess(String result) {
                    Log.e("onSuccess", result);
                    Intent intentBasic = new Intent(Config.STATE_LOADING);
                    intentBasic.putExtra(Config.STATE, Config.STATE_BASIC);
                    intentBasic.putExtra(Config.PROGRESS, 100);
                    sendBroadcast(intentBasic);
                    initPlayerData();
                }

                @Override
                public void onFailure(String result, Exception e) {
                    Log.e("onFailure", result);
                    if (Config.STATE_DESTROY.equals(result)) {
                        Intent intentBasic = new Intent(Config.STATE_LOADING);
                        intentBasic.putExtra(Config.STATE, Config.STATE_BASIC);
                        intentBasic.putExtra(Config.PROGRESS, -1);
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
        PlayerHandler handler = new PlayerHandler();
        final File file = new File(getFilesDir(), Config.FILE_PLAYER);
        if (!file.exists()) {
            try {
                file.createNewFile();
                int count = 0;
                app.getPlayerData().setLife(data.getLives().get(0));
                Intent intentLife = new Intent(Config.STATE_LOADING);
                intentLife.putExtra(Config.STATE, Config.STATE_PLAYER);
                intentLife.putExtra(Config.PROGRESS, (++count) * 10);
                sendBroadcast(intentLife);
                Thread.sleep(200);
                app.getPlayerData().setDefense(data.getDefenses().get(0));
                Intent intentDefense = new Intent(Config.STATE_LOADING);
                intentDefense.putExtra(Config.STATE, Config.STATE_PLAYER);
                intentDefense.putExtra(Config.PROGRESS, (++count) * 10);
                sendBroadcast(intentDefense);
                Thread.sleep(200);
                app.getPlayerData().setAgility(data.getAgilities().get(0));
                Intent intentAgility = new Intent(Config.STATE_LOADING);
                intentAgility.putExtra(Config.STATE, Config.STATE_PLAYER);
                intentAgility.putExtra(Config.PROGRESS, (++count) * 10);
                sendBroadcast(intentAgility);
                Thread.sleep(200);
                app.getPlayerData().setShield(data.getShields().get(0));
                Intent intentShield = new Intent(Config.STATE_LOADING);
                intentShield.putExtra(Config.STATE, Config.STATE_PLAYER);
                intentShield.putExtra(Config.PROGRESS, (++count) * 10);
                sendBroadcast(intentShield);
                Thread.sleep(200);
                app.getPlayerData().setPower(data.getPowers().get(0));
                Intent intentPower = new Intent(Config.STATE_LOADING);
                intentPower.putExtra(Config.STATE, Config.STATE_PLAYER);
                intentPower.putExtra(Config.PROGRESS, (++count) * 10);
                sendBroadcast(intentPower);
                Thread.sleep(200);
                app.getPlayerData().setSpeed(data.getSpeeds().get(0));
                Intent intentSpeed = new Intent(Config.STATE_LOADING);
                intentSpeed.putExtra(Config.STATE, Config.STATE_PLAYER);
                intentSpeed.putExtra(Config.PROGRESS, (++count) * 10);
                sendBroadcast(intentSpeed);
                Thread.sleep(200);
                app.getPlayerData().setRange(data.getRanges().get(0));
                Intent intentRange = new Intent(Config.STATE_LOADING);
                intentRange.putExtra(Config.STATE, Config.STATE_PLAYER);
                intentRange.putExtra(Config.PROGRESS, (++count) * 10);
                sendBroadcast(intentRange);
                Thread.sleep(200);
                app.getPlayerData().setBomb(data.getBombs().get(0));
                Intent intentBomb = new Intent(Config.STATE_LOADING);
                intentBomb.putExtra(Config.STATE, Config.STATE_PLAYER);
                intentBomb.putExtra(Config.PROGRESS, (++count) * 10);
                sendBroadcast(intentBomb);
                Thread.sleep(200);
                app.getPlayerData().setMoney(1000);
                Intent intentMoney = new Intent(Config.STATE_LOADING);
                intentMoney.putExtra(Config.STATE, Config.STATE_PLAYER);
                intentMoney.putExtra(Config.PROGRESS, 100);
                sendBroadcast(intentMoney);
                Thread.sleep(200);
                savePlayerData();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        } else {
            handler.setResource(app.getPlayerData(), file).read(new DataHandlerCallBack() {
                @Override
                public void onStart(String message) {
                    Log.e("onStart", message);
                }

                @Override
                public void onProcess(int progress) {
                    Log.e("onProcess", "" + progress);
                    Intent intentPlayer = new Intent(Config.STATE_LOADING);
                    intentPlayer.putExtra(Config.STATE, Config.STATE_PLAYER);
                    intentPlayer.putExtra(Config.PROGRESS, progress * 10);
                    sendBroadcast(intentPlayer);
                }

                @Override
                public void onSuccess(String result) {
                    Log.e("onSuccess", result);
                    Intent intentPlayer = new Intent(Config.STATE_LOADING);
                    intentPlayer.putExtra(Config.STATE, Config.STATE_PLAYER);
                    intentPlayer.putExtra(Config.PROGRESS, 100);
                    sendBroadcast(intentPlayer);
                }

                @Override
                public void onFailure(String result, Exception e) {
                    Log.e("onFailure", result);
                    if (Config.STATE_DESTROY.equals(result)) {
                        Intent intentPlayer = new Intent(Config.STATE_LOADING);
                        intentPlayer.putExtra(Config.STATE, Config.STATE_PLAYER);
                        intentPlayer.putExtra(Config.PROGRESS, -1);
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
    public void savePlayerData() {
        PlayerHandler handler = new PlayerHandler();
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
            }

            @Override
            public void onFailure(String result, Exception e) {
                Log.e("onFailure", result);
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
}
