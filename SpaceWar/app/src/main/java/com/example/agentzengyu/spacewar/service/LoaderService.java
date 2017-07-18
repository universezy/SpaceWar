package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.set.EnemyLibrary;
import com.example.agentzengyu.spacewar.entity.set.MapLibrary;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.set.ShopLibrary;
import com.example.agentzengyu.spacewar.entity.single.EnemyItem;
import com.example.agentzengyu.spacewar.entity.single.MapItem;
import com.example.agentzengyu.spacewar.entity.single.ShopItem;
import com.example.agentzengyu.spacewar.entity.single.UserInfo;

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
    }

    /**
     * 加载数据
     */
    private void loadData() {
        Log.e(TAG, "loadData");
        if (!loadShopData()) {
            return;
        }
        if (!loadPlayerData()) {
            return;
        }
        if (!loadEnemyData()) {
            return;
        }
        if (!loadMapData()) {
            return;
        }
    }

    /**
     * 加载商店数据
     */
    private boolean loadShopData() {
        Log.e(TAG, "loadShopData");
        ShopLibrary library = app.getShopDao().findAll();
        if (library == null) {
            Log.e("ShopData", "null");
            initShopData();
            library = app.getShopDao().findAll();
            if (library == null) {
                sendNotify(Constant.Init.Type.ERROR);
                return false;
            }
        }
        app.setShopLibrary(library);
        sendNotify(Constant.Init.Type.SHOP);
        return true;
    }

    /**
     * 加载玩家数据
     */
    private boolean loadPlayerData() {
        Log.e(TAG, "loadPlayerData");
        PlayerData data = app.getPlayerDao().findAll();
        if (data == null) {
            Log.e("PlayerData", "null");
            initPlayerData();
            data = app.getPlayerDao().findAll();
            if (data == null) {
                sendNotify(Constant.Init.Type.ERROR);
                return false;
            }
        }
        app.setPlayerData(data);
        sendNotify(Constant.Init.Type.PLAYER);
        return true;
    }

    /**
     * 加载敌人数据
     */
    private boolean loadEnemyData() {
        Log.e(TAG, "loadEnemyData");
        EnemyLibrary library = app.getEnemyDao().findAll();
        if (library == null) {
            Log.e("EnemyData", "null");
            initEnemyData();
            library = app.getEnemyDao().findAll();
            if (library == null) {
                sendNotify(Constant.Init.Type.ERROR);
                return false;
            }
        }
        app.setEnemyLibrary(library);
        sendNotify(Constant.Init.Type.ENEMY);
        return true;
    }

    /**
     * 加载地图数据
     */
    private boolean loadMapData() {
        Log.e(TAG, "loadMapData");
        MapLibrary library = app.getMapDao().findAll();
        if (library == null) {
            Log.e("MapData", "null");
            initMapData();
            library = app.getMapDao().findAll();
            if (library == null) {
                sendNotify(Constant.Init.Type.ERROR);
                return false;
            }
        }
        app.setMapLibrary(library);
        sendNotify(Constant.Init.Type.MAP);
        return true;
    }

    /**
     * 初始化商店数据
     *
     * @return
     */
    private void initShopData() {
        Log.e(TAG, "initShopData");
        ShopItem life1 = new ShopItem("life1", 100, 100, 100, R.mipmap.life1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.LIFE, life1);

        ShopItem defense1 = new ShopItem("defense1", 100, 100, 100, R.mipmap.life1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.DEFENSE, defense1);

        ShopItem agility1 = new ShopItem("agility1", 100, 100, 100, R.mipmap.life1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.AGILITY, agility1);

        ShopItem shield1 = new ShopItem("shield1", 100, 100, 100, R.mipmap.ic_launcher_round);
        app.getShopDao().insert(Constant.Database.Shop.TableName.SHIELD, shield1);

        ShopItem power1 = new ShopItem("power1", 100, 100, 100, R.mipmap.life1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.POWER, power1);

        ShopItem speed1 = new ShopItem("speed1", 100, 100, 100, R.mipmap.life1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.SPEED, speed1);

        ShopItem range1 = new ShopItem("range1", 100, 100, 100, R.mipmap.life1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.RANGE, range1);

        ShopItem laser1 = new ShopItem("laser1", 100, 100, 100, R.mipmap.life1);
        app.getShopDao().insert(Constant.Database.Shop.TableName.LASER, laser1);
    }

    /**
     * 初始化玩家数据
     *
     * @return
     */
    private void initPlayerData() {
        Log.e(TAG, "initPlayerData");
        PlayerData data = new PlayerData();
        if (data.setLife(app.getShopLibrary().getLives().get(0)) &&
                data.setDefense(app.getShopLibrary().getDefenses().get(0)) &&
                data.setAgility(app.getShopLibrary().getAgilities().get(0)) &&
                data.setShield(app.getShopLibrary().getShields().get(0)) &&
                data.setPower(app.getShopLibrary().getPowers().get(0)) &&
                data.setSpeed(app.getShopLibrary().getSpeeds().get(0)) &&
                data.setRange(app.getShopLibrary().getRanges().get(0)) &&
                data.setLaser(app.getShopLibrary().getLasers().get(0)) &&
                data.setInfo(new UserInfo("New User", 1000))) {
            app.getPlayerDao().update(data);
        }
    }

    /**
     * 初始化敌人数据
     *
     * @return
     */
    private void initEnemyData() {
        Log.e(TAG, "initEnemyData");
        EnemyItem item1 = new EnemyItem("enemy1", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item1);

        EnemyItem item2 = new EnemyItem("enemy1", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item2);

        EnemyItem item3 = new EnemyItem("enemy1", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item3);

        EnemyItem item4 = new EnemyItem("enemy1", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item4);

        EnemyItem item5 = new EnemyItem("enemy1", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item5);

        EnemyItem item6 = new EnemyItem("enemy1", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item6);

        EnemyItem item7 = new EnemyItem("enemy1", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item7);

        EnemyItem item8 = new EnemyItem("enemy1", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item8);

        EnemyItem boss1 = new EnemyItem("enemy1", 300, 200, 100, 100, 100, 100, R.mipmap.ic_launcher_round);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.BOSS, boss1);

        EnemyItem boss2 = new EnemyItem("enemy1", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher_round);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.BOSS, boss2);
    }

    /**
     * 初始化地图数据
     *
     * @return
     */
    private void initMapData() {
        Log.e(TAG, "initMapData");
        MapItem item1 = new MapItem("map1", R.mipmap.ic_launcher, R.raw.dingding, "boss1");
        //TODO
        app.getMapDao().insert(item1);

        MapItem item2 = new MapItem("map2", R.mipmap.ic_launcher, R.raw.dingding, "boss2");
        //TODO
        app.getMapDao().insert(item2);

        MapItem item3 = new MapItem("map3", R.mipmap.ic_launcher, R.raw.dingding, "boss3");
        //TODO
        app.getMapDao().insert(item3);

        MapItem item4 = new MapItem("map4", R.mipmap.ic_launcher, R.raw.dingding, "boss4");
        //TODO
        app.getMapDao().insert(item4);

        MapItem item5 = new MapItem("map5", R.mipmap.ic_launcher, R.raw.dingding, "boss5");
        //TODO
        app.getMapDao().insert(item5);
    }

    /**
     * 发送通知
     *
     * @param target 广播目标
     */
    private void sendNotify(@Constant.Init.Type String target) {
        Intent intent = new Intent(Constant.Init.TAG);
        intent.putExtra(Constant.BroadCast.TARGET, target);
        sendBroadcast(intent);
    }
}
