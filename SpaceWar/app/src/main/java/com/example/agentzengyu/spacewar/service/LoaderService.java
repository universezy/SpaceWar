package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.database.enemy.EnemyDaoImpl;
import com.example.agentzengyu.spacewar.database.map.MapDaoImpl;
import com.example.agentzengyu.spacewar.database.player.PlayerDaoImpl;
import com.example.agentzengyu.spacewar.database.shop.ShopDaoImpl;
import com.example.agentzengyu.spacewar.entity.set.EnemyLibrary;
import com.example.agentzengyu.spacewar.entity.set.MapLibrary;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.set.ShopLibrary;
import com.example.agentzengyu.spacewar.entity.single.UserInfo;

/**
 * 初始化服务
 */
public class LoaderService extends Service {
    private final String TAG = getClass().getName();
    private SpaceWarApp app = null;
    private ShopDaoImpl shopDao = null;
    private PlayerDaoImpl playerDao = null;
    private EnemyDaoImpl enemyDao = null;
    private MapDaoImpl mapDao = null;

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate.");
        super.onCreate();
        app = (SpaceWarApp) getApplication();
        app.setLoaderService(this);
        shopDao = ShopDaoImpl.getInstance(getApplicationContext());
        playerDao = PlayerDaoImpl.getInstance(getApplicationContext());
        enemyDao = EnemyDaoImpl.getInstance(getApplicationContext());
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG, "onStart.");
        super.onStart(intent, startId);
        loadData();
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

    /**
     * 加载数据
     */
    private void loadData() {
        loadShopData();
        loadPlayerData();
        loadEnemyData();
        loadMapData();
    }

    /**
     * 加载商店数据
     */
    private void loadShopData() {
        ShopLibrary library = shopDao.findAll();
        if (library == null) {
            initShopData(library);
        }
        app.setShopLibrary(library);
    }

    /**
     * 加载玩家数据
     */
    private void loadPlayerData() {
        PlayerData data = playerDao.findAll();
        if (data == null) {
            initPlayerData(data);
        }
        app.setPlayerData(data);
    }

    /**
     * 加载敌人数据
     */
    private void loadEnemyData() {
        EnemyLibrary library = enemyDao.findAll();
        if (library == null) {
            initEnemyData(library);
        }
        app.setEnemyLibrary(library);
    }

    /**
     * 加载地图数据
     */
    private void loadMapData() {
        MapLibrary library = mapDao.findAll();
        if (library == null) {
            initMapData(library);
        }
        app.setMapLibrary(library);
    }

    /**
     * 初始化商店数据
     *
     * @param library
     */
    private void initShopData(ShopLibrary library) {


        // TODO shopDao.insert()
    }

    /**
     * 初始化玩家数据
     *
     * @param data
     */
    private void initPlayerData(PlayerData data) {
        data.setLife(app.getShopLibrary().getLives().get(0));
        data.setDefense(app.getShopLibrary().getDefenses().get(0));
        data.setAgility(app.getShopLibrary().getAgilities().get(0));
        data.setShield(app.getShopLibrary().getShields().get(0));
        data.setPower(app.getShopLibrary().getPowers().get(0));
        data.setSpeed(app.getShopLibrary().getSpeeds().get(0));
        data.setRange(app.getShopLibrary().getRanges().get(0));
        data.setLaser(app.getShopLibrary().getLasers().get(0));
        data.setInfo(new UserInfo("New User", 1000));
        playerDao.update(data);
    }

    /**
     * 初始化敌人数据
     *
     * @param library
     */
    private void initEnemyData(EnemyLibrary library) {


        // TODO enemyDao.insert()
    }

    /**
     * 初始化地图数据
     *
     * @param library
     */
    private void initMapData(MapLibrary library) {


        // TODO mapDao.insert()
    }

    /**
     * 关闭数据库
     */
    private void closeDatabase() {
        if (shopDao != null) {
            shopDao.close();
        }
        if (playerDao != null) {
            playerDao.close();
        }
        if (enemyDao != null) {
            enemyDao.close();
        }
        if (mapDao != null) {
            mapDao.close();
        }
    }
}
