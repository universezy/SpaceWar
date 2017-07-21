package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.set.ArticleLibrary;
import com.example.agentzengyu.spacewar.entity.set.EnemyLibrary;
import com.example.agentzengyu.spacewar.entity.set.LevelLibrary;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.set.RelevancyLibrary;
import com.example.agentzengyu.spacewar.entity.single.Article;
import com.example.agentzengyu.spacewar.entity.single.Enemy;
import com.example.agentzengyu.spacewar.entity.single.Level;
import com.example.agentzengyu.spacewar.entity.single.Player;
import com.example.agentzengyu.spacewar.entity.single.Relevancy;

/**
 * 初始化服务
 */
public class LoaderService extends Service implements ILoader{
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

    @Override
    public void loadData() {
        Log.e(TAG, "loadData");
        if (!loadArticleData()) {
            return;
        }
        if (!loadPlayerData()) {
            return;
        }
        if (!loadEnemyData()) {
            return;
        }
        if (!loadLevelData()) {
            return;
        }
        if (!loadRelevancyData()){
            return;
        }
    }

    @Override
    public boolean loadArticleData() {
        Log.e(TAG, "loadArticleData");
        ArticleLibrary library = app.getArticleDao().findAll();
        if (library == null) {
            Log.e("ShopData", "null");
            initArcitleData();
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

    @Override
    public boolean loadPlayerData() {
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

    @Override
    public boolean loadEnemyData() {
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

    @Override
    public boolean loadLevelData() {
        Log.e(TAG, "loadLevelData");
        LevelLibrary library = app.getLevelDao().findAll();
        if (library == null) {
            Log.e("LevelData", "null");
            initLevelData();
            library = app.getLevelDao().findAll();
            if (library == null) {
                sendNotify(Constant.Init.Type.ERROR);
                return false;
            }
        }
        app.setLevelLibrary(library);
        sendNotify(Constant.Init.Type.LEVEL);
        return true;
    }

    @Override
    public boolean loadRelevancyData(){
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

    @Override
    public void initArcitleData() {
        Log.e(TAG, "initArcitleData");
        Article life1 = new Article("life1", R.mipmap.ic_launcher_round, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.LIFE, life1);

        Article defense1 = new Article("defense1", R.mipmap.ic_launcher, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.DEFENSE, defense1);

        Article agility1 = new Article("agility1", R.mipmap.life1, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.VELOCITY, agility1);

        Article shield1 = new Article("shield1", R.mipmap.ic_launcher_round, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.SHIELD, shield1);

        Article power1 = new Article("power1", R.mipmap.life1, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.POWER, power1);

        Article speed1 = new Article("speed1", R.mipmap.life1, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.SPEED, speed1);

        Article range1 = new Article("range1", R.mipmap.life1, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.RANGE, range1);

        Article laser1 = new Article("laser1", R.mipmap.life1, 100, 100, 100);
        app.getArticleDao().insert(Constant.Database.Article.TableName.LASER, laser1);
    }

    @Override
    public void initPlayerData() {
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
                data.setInfo(new Player("New User", 1000))) {
            app.getPlayerDao().update(data);
        }
    }

    @Override
    public void initEnemyData() {
        Log.e(TAG, "initEnemyData");
        Enemy enemy1 = new Enemy("enemy1", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy1);

        Enemy enemy2 = new Enemy("enemy2", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy2);

        Enemy enemy3 = new Enemy("enemy3", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy3);

        Enemy enemy4 = new Enemy("enemy4", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy4);

        Enemy enemy5 = new Enemy("enemy5", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy5);

        Enemy enemy6 = new Enemy("enemy6", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy6);

        Enemy enemy7 = new Enemy("enemy7", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy7);

        Enemy enemy8 = new Enemy("enemy8", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy8);

        Enemy boss1 = new Enemy("boss1", 300, 200, 100, 100, 100, 100, R.mipmap.ic_launcher_round);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.BOSS, boss1);

        Enemy boss2 = new Enemy("boss2", 100, 100, 100, 100, 100, 100, R.mipmap.ic_launcher_round);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.BOSS, boss2);
    }

    @Override
    public void initLevelData() {
        Log.e(TAG, "initLevelData");
        Level level1 = new Level("level1", R.mipmap.ic_launcher, R.raw.dingding, "boss1");
        app.getLevelDao().insert(level1);

        Level level2 = new Level("level2", R.mipmap.ic_launcher_round, R.raw.dingding, "boss2");
        app.getLevelDao().insert(level2);

        Level level3 = new Level("level3", R.mipmap.ic_launcher, R.raw.dingding, "boss3");
        app.getLevelDao().insert(level3);
    }

    @Override
    public void initRelevancy(){
        Relevancy relevancy1 = new Relevancy("level1","enemy1",2);
        app.getRelevancyDao().insert(relevancy1);

        Relevancy relevancy2 = new Relevancy("level2","enemy2",3);
        app.getRelevancyDao().insert(relevancy2);

        Relevancy relevancy3 = new Relevancy("level3","enemy3",1);
        app.getRelevancyDao().insert(relevancy3);
    }

    @Override
    public void bindData() {

    }

    @Override
    public void sendNotify(@Constant.Init.Type String target) {
        Intent intent = new Intent(Constant.Init.TAG);
        intent.putExtra(Constant.BroadCast.TARGET, target);
        sendBroadcast(intent);
    }
}
