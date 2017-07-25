package com.example.agentzengyu.spacewar.application;

import android.app.Application;
import android.util.Log;

import com.example.agentzengyu.spacewar.database.article.ArticleDaoImpl;
import com.example.agentzengyu.spacewar.database.enemy.EnemyDaoImpl;
import com.example.agentzengyu.spacewar.database.level.LevelDaoImpl;
import com.example.agentzengyu.spacewar.database.player.PlayerDaoImpl;
import com.example.agentzengyu.spacewar.database.relevancy.RelevancyDaoImpl;
import com.example.agentzengyu.spacewar.entity.set.ArticleLibrary;
import com.example.agentzengyu.spacewar.entity.set.EnemyLibrary;
import com.example.agentzengyu.spacewar.entity.set.LevelLibrary;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.set.RelevancyLibrary;
import com.example.agentzengyu.spacewar.service.GameService;
import com.example.agentzengyu.spacewar.service.LoaderService;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 应用类，全局管理
 */
public class SpaceWarApp extends Application {
    private static String TAG = null;

    private LoaderService loaderService = null;
    private GameService gameGameService = null;

    private ArticleLibrary articleLibrary = null;
    private PlayerData playerData = null;
    private EnemyLibrary enemyLibrary = null;
    private LevelLibrary levelLibrary = null;
    private RelevancyLibrary relevancyLibrary = null;

    private ArticleDaoImpl articleDao = null;
    private PlayerDaoImpl playerDao = null;
    private EnemyDaoImpl enemyDao = null;
    private LevelDaoImpl levelDao = null;
    private RelevancyDaoImpl relevancyDao = null;

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = getApplicationContext().getClass().getName();
        Log.e(TAG, "onCreate.");
        articleDao = ArticleDaoImpl.getInstance(getApplicationContext());
        playerDao = PlayerDaoImpl.getInstance(getApplicationContext());
        enemyDao = EnemyDaoImpl.getInstance(getApplicationContext());
        levelDao = LevelDaoImpl.getInstance(getApplicationContext());
        relevancyDao = RelevancyDaoImpl.getInstance(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e(TAG, "onTerminate.");
        destroyInitService();
        destroyGameService();
        closeDatabase();
    }

    public void setArticleLibrary(ArticleLibrary articleLibrary) {
        this.articleLibrary = articleLibrary;
    }

    public void setPlayerData(PlayerData playerData) {
        this.playerData = playerData;
    }

    public void setEnemyLibrary(EnemyLibrary enemyLibrary) {
        this.enemyLibrary = enemyLibrary;
    }

    public void setLevelLibrary(LevelLibrary levelLibrary) {
        this.levelLibrary = levelLibrary;
    }

    public void setRelevancyLibrary(RelevancyLibrary relevancyLibrary) {
        this.relevancyLibrary = relevancyLibrary;
    }

    public ArticleLibrary getArticleLibrary() {
        return articleLibrary;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public EnemyLibrary getEnemyLibrary() {
        return enemyLibrary;
    }

    public LevelLibrary getLevelLibrary() {
        return levelLibrary;
    }

    public RelevancyLibrary getRelevancyLibrary() {
        return relevancyLibrary;
    }

    public ArticleDaoImpl getArticleDao() {
        return articleDao;
    }

    public PlayerDaoImpl getPlayerDao() {
        return playerDao;
    }

    public EnemyDaoImpl getEnemyDao() {
        return enemyDao;
    }

    public LevelDaoImpl getLevelDao() {
        return levelDao;
    }

    public RelevancyDaoImpl getRelevancyDao() {
        return relevancyDao;
    }

    /**
     * 设置初始化服务
     *
     * @param loaderService
     */
    public void setLoaderService(LoaderService loaderService) {
        this.loaderService = loaderService;
    }

    /**
     * 获取初始化服务对象
     *
     * @return
     */
    public LoaderService getLoaderService() {
        return loaderService;
    }

    /**
     * 销毁初始化服务
     */
    public void destroyInitService() {
        if (loaderService != null) {
            loaderService.stopSelf();
            loaderService = null;
        }
    }

    /**
     * 设置游戏服务
     *
     * @param gameGameService
     */
    public void setGameGameService(GameService gameGameService) {
        this.gameGameService = gameGameService;
    }

    /**
     * 获取游戏服务对象
     *
     * @return
     */
    public GameService getGameGameService() {
        return gameGameService;
    }

    /**
     * 销毁初始化服务
     */
    public void destroyGameService() {
        if (gameGameService != null) {
            gameGameService.stopSelf();
            gameGameService = null;
        }
    }

    /**
     * 关闭数据库
     */
    private void closeDatabase() {
        Log.e(TAG, "closeDatabase");
        if (articleDao != null) {
            articleDao.close();
        }
        if (playerDao != null) {
            playerDao.close();
        }
        if (enemyDao != null) {
            enemyDao.close();
        }
        if (levelDao != null) {
            levelDao.close();
        }
        if (relevancyDao != null) {
            relevancyDao.close();
        }
    }
}
