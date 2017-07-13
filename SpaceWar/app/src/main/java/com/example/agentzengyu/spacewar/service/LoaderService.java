package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.database.player.PlayerDaoImpl;
import com.example.agentzengyu.spacewar.database.shop.ShopDaoImpl;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.set.ShopLibrary;
import com.example.agentzengyu.spacewar.entity.single.UserInfo;

/**
 * 初始化服务
 */
public class LoaderService extends Service {
    private final String TAG = getClass().getName();
    private SpaceWarApp app = null;
    private PlayerDaoImpl playerDao = null;
    private ShopDaoImpl shopDao = null;

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate.");
        super.onCreate();
        app = (SpaceWarApp) getApplication();
        app.setLoaderService(this);
        playerDao = PlayerDaoImpl.getInstance(getApplicationContext());
        shopDao = ShopDaoImpl.getInstance(getApplicationContext());
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG, "onStart.");
        super.onStart(intent, startId);
        loadData();
        // initShopData();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy.");
        super.onDestroy();
        closeDatabase();
    }

    private void loadData() {
        loadShopData();
        loadPlayerData();
        loadMapData();
        loadEnemyData();
    }

    private void loadShopData() {
        ShopLibrary library = shopDao.findAll();
        if (library != null) {
            app.setShopLibrary(library);
        } else {

        }
    }

    private void loadPlayerData() {
        PlayerData data = playerDao.findAll();
        if (data == null) {
            data.setLife(app.getShopLibrary().getLives().get(0));
            data.setDefense(app.getShopLibrary().getDefenses().get(0));
            data.setAgility(app.getShopLibrary().getAgilities().get(0));
            data.setShield(app.getShopLibrary().getShields().get(0));
            data.setPower(app.getShopLibrary().getPowers().get(0));
            data.setSpeed(app.getShopLibrary().getSpeeds().get(0));
            data.setRange(app.getShopLibrary().getRanges().get(0));
            data.setLaser(app.getShopLibrary().getLasers().get(0));
            data.setInfo(new UserInfo("New User", 1000));
        }
        app.setPlayerData(data);
    }

    private void loadMapData() {

    }

    private void loadEnemyData() {

    }

    private void closeDatabase() {
        if (shopDao != null) {
            shopDao.close();
        }
        if (playerDao != null) {
            playerDao.close();
        }
    }
}
