package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
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

import java.util.List;

/**
 * 初始化服务
 */
public class LoaderService extends Service implements ILoader {
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
        if (!loadRelevancyData()) {
            return;
        }
        if (!bindData()) {
            return;
        }
    }

    @Override
    public boolean loadArticleData() {
        Log.e(TAG, "loadArticleData");
        ArticleLibrary library = app.getArticleDao().findAll();
        if (library == null) {
            Log.e("ArticleData", "null");
            initArticleData();
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
    public boolean loadRelevancyData() {
        Log.e(TAG, "loadRelevancyData");
        RelevancyLibrary library = app.getRelevancyDao().findAll();
        if (library == null) {
            Log.e("RelevancyData", "null");
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
    public void initArticleData() {
        Log.e(TAG, "initArticleData");
        /************************ Grade 1 ************************/
        Article life1 = new Article("life1", R.mipmap.life1, 1000, 1, 1000);
        app.getArticleDao().insert(Constant.Database.Article.TableName.LIFE, life1);

        Article defense1 = new Article("defense1", R.mipmap.defense1, 200, 1, 1000);
        app.getArticleDao().insert(Constant.Database.Article.TableName.DEFENSE, defense1);

        Article velocity1 = new Article("velocity1", R.mipmap.velocity1, 100, 1, 1000);
        app.getArticleDao().insert(Constant.Database.Article.TableName.VELOCITY, velocity1);

        Article shield1 = new Article("shield1", R.mipmap.shield1, 30, 1, 1000);
        app.getArticleDao().insert(Constant.Database.Article.TableName.SHIELD, shield1);

        Article power1 = new Article("power1", R.mipmap.power1, 200, 1, 1000);
        app.getArticleDao().insert(Constant.Database.Article.TableName.POWER, power1);

        Article speed1 = new Article("speed1", R.mipmap.speed1, 50, 1, 1000);
        app.getArticleDao().insert(Constant.Database.Article.TableName.SPEED, speed1);

        Article laser1 = new Article("laser1", R.mipmap.laser1, 30, 1, 1000);
        app.getArticleDao().insert(Constant.Database.Article.TableName.LASER, laser1);

        /************************ Grade 2 ************************/
        Article life2 = new Article("life2", R.mipmap.life1, 1200, 2, 1200);
        app.getArticleDao().insert(Constant.Database.Article.TableName.LIFE, life2);

        Article defense2 = new Article("defense2", R.mipmap.defense1, 220, 2, 1200);
        app.getArticleDao().insert(Constant.Database.Article.TableName.DEFENSE, defense2);

        Article velocity2 = new Article("velocity2", R.mipmap.velocity1, 120, 2, 1200);
        app.getArticleDao().insert(Constant.Database.Article.TableName.VELOCITY, velocity2);

        Article shield2 = new Article("shield2", R.mipmap.shield1, 28, 2, 1200);
        app.getArticleDao().insert(Constant.Database.Article.TableName.SHIELD, shield2);

        Article power2 = new Article("power2", R.mipmap.power1, 250, 2, 1200);
        app.getArticleDao().insert(Constant.Database.Article.TableName.POWER, power2);

        Article speed2 = new Article("speed2", R.mipmap.speed1, 55, 2, 1200);
        app.getArticleDao().insert(Constant.Database.Article.TableName.SPEED, speed2);

        Article laser2 = new Article("laser2", R.mipmap.laser1, 28, 2, 1200);
        app.getArticleDao().insert(Constant.Database.Article.TableName.LASER, laser2);
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
                data.setLaser(app.getArticleLibrary().getLasers().get(0)) &&
                data.setInfo(new Player("New User", 2000, R.mipmap.playership, R.mipmap.playercrash, R.mipmap.playerbullet))) {
            app.getPlayerDao().update(data);
        }
    }

    @Override
    public void initEnemyData() {
        Log.e(TAG, "initEnemyData");
        Enemy enemy1 = new Enemy("enemy1", R.mipmap.enemyship, R.mipmap.enemycrash, R.mipmap.enemybullet, 200, 40, 300, 20, 300);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy1);

        Enemy enemy2 = new Enemy("enemy2", R.mipmap.enemyship, R.mipmap.enemycrash, R.mipmap.enemybullet, 400, 80, 250, 30, 280);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy2);

        Enemy enemy3 = new Enemy("enemy3", R.mipmap.enemyship, R.mipmap.enemycrash, R.mipmap.enemybullet, 600, 160, 200, 40, 260);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy3);

        Enemy enemy4 = new Enemy("enemy4", R.mipmap.enemyship, R.mipmap.enemycrash, R.mipmap.enemybullet, 800, 200, 150, 50, 240);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy4);

        Enemy enemy5 = new Enemy("enemy5", R.mipmap.enemyship, R.mipmap.enemycrash, R.mipmap.enemybullet, 1000, 240, 150, 60, 220);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy5);

        Enemy enemy6 = new Enemy("enemy6", R.mipmap.enemyship, R.mipmap.enemycrash, R.mipmap.enemybullet, 2000, 280, 150, 70, 200);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy6);

        Enemy enemy7 = new Enemy("enemy7", R.mipmap.enemyship, R.mipmap.enemycrash, R.mipmap.enemybullet, 4000, 320, 100, 80, 180);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy7);

        Enemy enemy8 = new Enemy("enemy8", R.mipmap.enemyship, R.mipmap.enemycrash, R.mipmap.enemybullet, 8000, 360, 50, 90, 160);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.NORMAL, enemy8);

        Enemy boss1 = new Enemy("boss1", R.mipmap.bossship, R.mipmap.bosscrash, R.mipmap.bossbullet, 50000, 400, 30, 200, 150);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.BOSS, boss1);

        Enemy boss2 = new Enemy("boss2", R.mipmap.bossship, R.mipmap.bosscrash, R.mipmap.bossbullet, 50000, 400, 30, 200, 150);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.BOSS, boss2);

        Enemy boss3 = new Enemy("boss3", R.mipmap.bossship, R.mipmap.bosscrash, R.mipmap.bossbullet, 50000, 400, 30, 200, 150);
        app.getEnemyDao().insert(Constant.Database.Enemy.TableName.BOSS, boss3);
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
    public void initRelevancy() {
        Log.e(TAG, "initRelevancy");
        Relevancy relevancy1 = new Relevancy("level1", "enemy1", 30);
        app.getRelevancyDao().insert(relevancy1);

        Relevancy relevancy2 = new Relevancy("level2", "enemy2", 50);
        app.getRelevancyDao().insert(relevancy2);

        Relevancy relevancy3 = new Relevancy("level3", "enemy1", 16);
        app.getRelevancyDao().insert(relevancy3);

        Relevancy relevancy4 = new Relevancy("level3", "enemy2", 14);
        app.getRelevancyDao().insert(relevancy4);

        Relevancy relevancy5 = new Relevancy("level3", "enemy3", 12);
        app.getRelevancyDao().insert(relevancy5);

        Relevancy relevancy6 = new Relevancy("level3", "enemy4", 10);
        app.getRelevancyDao().insert(relevancy6);

        Relevancy relevancy7 = new Relevancy("level3", "enemy5", 8);
        app.getRelevancyDao().insert(relevancy7);

        Relevancy relevancy8 = new Relevancy("level3", "enemy6", 6);
        app.getRelevancyDao().insert(relevancy8);

        Relevancy relevancy9 = new Relevancy("level3", "enemy7", 4);
        app.getRelevancyDao().insert(relevancy9);

        Relevancy relevancy10 = new Relevancy("level3", "enemy8", 2);
        app.getRelevancyDao().insert(relevancy10);
    }

    @Override
    public boolean bindData() {
        List<Relevancy> relevancies = app.getRelevancyLibrary().getRelevancies();
        if (relevancies == null) {
            sendNotify(Constant.Init.Type.ERROR);
            return false;
        }
        if (relevancies.size() == 0) {
            sendNotify(Constant.Init.Type.ERROR);
            return false;
        }
        for (int j = 0; j < relevancies.size(); j++) {
            Relevancy relevancy = relevancies.get(j);
            Level level = app.getLevelLibrary().getLevels().get(relevancy.getLevelName());
            Enemy enemy = app.getEnemyLibrary().getNormalEnemies().get(relevancy.getEnemyName());
            for (int i = 0; i < relevancy.getEnemyCount(); i++) {
                level.getEnemies().add(enemy);
            }
            level.setBoss(app.getEnemyLibrary().getBossEnemies().get(level.getBossName()));
        }
        sendNotify(Constant.Init.Type.BIND);
        return true;
    }

    @Override
    public void sendNotify(@Constant.Init.Type final String target) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Constant.Init.TAG);
                intent.putExtra(Constant.BroadCast.TARGET, target);
                sendBroadcast(intent);
            }
        }, 300);
    }
}
