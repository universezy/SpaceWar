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
import com.example.agentzengyu.spacewar.entity.set.ArticleLibrary;
import com.example.agentzengyu.spacewar.entity.set.RelevancyLibrary;
import com.example.agentzengyu.spacewar.entity.single.Enemy;
import com.example.agentzengyu.spacewar.entity.single.Map;
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
        if (!loadRelevancyData()){
            return;
        }
    }

    /**
     * 加载商店数据
     */
    private boolean loadShopData() {
        Log.e(TAG, "loadShopData");
        ArticleLibrary library = app.getArticleDao().findAll();
        if (library == null) {
            Log.e("ShopData", "null");
            initShopData();
            library = app.getArticleDao().findAll();
            if (library == null) {
                sendNotify(Constant.Init.Type.ERROR);
                return false;
            }
        }
        app.setArticleLibrary(library);
        sendNotify(Constant.Init.Type.ARTICLE);
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
     * 加载关联数据
     */
    private boolean loadRelevancyData(){
        Log.e(TAG, "loadRelevancyData");
        RelevancyLibrary library = app.getRelevancyDao().findAll();
        if (library==null){
            Log.e("RelevancyData","null");
            initRelevancy();
            library = app.getRelevancyDao().findAll();
            if (library == null) {
                sendNotify(Constant.Init.Type.ERROR);
                return false;
            }
        }
        app.setRelevancyLibrary(library);
        sendNotify(Constant.Init.Type.RELEVANCY);
        return true;
    }

    /**
     * 初始化商店数据
     *
     * @return
     */
    private void initShopData() {
        Log.e(TAG, "initShopData");
        com.example.agentzengyu.spacewar.entity.single.Article life1 = new com.example.agentzengyu.spacewar.entity.single.Article("life1", R.mipmap.ic_launcher_round, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.LIFE, life1);

        com.example.agentzengyu.spacewar.entity.single.Article defense1 = new com.example.agentzengyu.spacewar.entity.single.Article("defense1", R.mipmap.ic_launcher, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.DEFENSE, defense1);

        com.example.agentzengyu.spacewar.entity.single.Article agility1 = new com.example.agentzengyu.spacewar.entity.single.Article("agility1", R.mipmap.life1, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.VELOCITY, agility1);

        com.example.agentzengyu.spacewar.entity.single.Article shield1 = new com.example.agentzengyu.spacewar.entity.single.Article("shield1", R.mipmap.ic_launcher_round, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.SHIELD, shield1);

        com.example.agentzengyu.spacewar.entity.single.Article power1 = new com.example.agentzengyu.spacewar.entity.single.Article("power1", R.mipmap.life1, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.POWER, power1);

        com.example.agentzengyu.spacewar.entity.single.Article speed1 = new com.example.agentzengyu.spacewar.entity.single.Article("speed1", R.mipmap.life1, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.SPEED, speed1);

        com.example.agentzengyu.spacewar.entity.single.Article range1 = new com.example.agentzengyu.spacewar.entity.single.Article("range1", R.mipmap.life1, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.RANGE, range1);

        com.example.agentzengyu.spacewar.entity.single.Article laser1 = new com.example.agentzengyu.spacewar.entity.single.Article("laser1", R.mipmap.life1, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.LASER, laser1);
    }

    /**
     * 初始化玩家数据
     *
     * @return
     */
    private void initPlayerData() {
        Log.e(TAG, "initPlayerData");
        PlayerData data = new PlayerData();
        if (data.setLife(app.getArticleLibrary().getLives().get(0)) &&
                data.setDefense(app.getArticleLibrary().getDefenses().get(0)) &&
                data.setVelocity(app.getArticleLibrary().getVelocities().get(0)) &&
                data.setShield(app.getArticleLibrary().getShields().get(0)) &&
                data.setPower(app.getArticleLibrary().getPowers().get(0)) &&
                data.setSpeed(app.getArticleLibrary().getSpeeds().get(0)) &&
                data.setRange(app.getArticleLibrary().getRanges().get(0)) &&
                data.setLaser(app.getArticleLibrary().getLasers().get(0)) &&
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
        Enemy item1 = new Enemy("enemy1", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item1);

        Enemy item2 = new Enemy("enemy2", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item2);

        Enemy item3 = new Enemy("enemy3", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item3);

        Enemy item4 = new Enemy("enemy4", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item4);

        Enemy item5 = new Enemy("enemy5", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item5);

        Enemy item6 = new Enemy("enemy6", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item6);

        Enemy item7 = new Enemy("enemy7", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item7);

        Enemy item8 = new Enemy("enemy8", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, item8);

        Enemy boss1 = new Enemy("boss1", 300, 200, 100, 100, 100, 100, R.mipmap.ic_launcher_round);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.BOSS, boss1);

        Enemy boss2 = new Enemy("boss2", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher_round);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.BOSS, boss2);
    }

    /**
     * 初始化地图数据
     *
     * @return
     */
    private void initMapData() {
        Log.e(TAG, "initMapData");
        Map item1 = new Map("map1", R.mipmap.ic_launcher, R.raw.dingding, "boss1");
        app.getMapDao().insert(item1);

        Map item2 = new Map("map2", R.mipmap.ic_launcher_round, R.raw.dingding, "boss2");
        app.getMapDao().insert(item2);

        Map item3 = new Map("map3", R.mipmap.ic_launcher, R.raw.dingding, "boss3");
        app.getMapDao().insert(item3);
    }

    /**
     * 初始化关联数据
     *
     * @return
     */
    private void initRelevancy(){

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
