package com.example.agentzengyu.spacewar.application;

import android.app.Application;
import android.util.Log;

import com.example.agentzengyu.spacewar.database.enemy.EnemyDaoImpl;
import com.example.agentzengyu.spacewar.database.map.MapDaoImpl;
import com.example.agentzengyu.spacewar.database.player.PlayerDaoImpl;
import com.example.agentzengyu.spacewar.database.shop.ShopDaoImpl;
import com.example.agentzengyu.spacewar.entity.set.EnemyLibrary;
import com.example.agentzengyu.spacewar.entity.set.MapLibrary;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.set.ShopLibrary;
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
    private GameService gameService = null;

    private ShopLibrary shopLibrary = null;
    private PlayerData playerData = null;
    private MapLibrary mapLibrary = null;
    private EnemyLibrary enemyLibrary = null;

    private ShopDaoImpl shopDao = null;
    private PlayerDaoImpl playerDao = null;
    private EnemyDaoImpl enemyDao = null;
    private MapDaoImpl mapDao = null;

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = getApplicationContext().getClass().getName();
        Log.e(TAG, "onCreate.");
        shopDao = ShopDaoImpl.getInstance(getApplicationContext());
        playerDao = PlayerDaoImpl.getInstance(getApplicationContext());
        enemyDao = EnemyDaoImpl.getInstance(getApplicationContext());
        mapDao = MapDaoImpl.getInstance(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e(TAG, "onTerminate.");
        destroyInitService();
        destroyGameService();
        closeDatabase();
    }

    public void setShopLibrary(ShopLibrary shopLibrary) {
        this.shopLibrary = shopLibrary;
    }

    public void setPlayerData(PlayerData playerData) {
        this.playerData = playerData;
    }

    public void setMapLibrary(MapLibrary mapLibrary) {
        this.mapLibrary = mapLibrary;
    }

    public void setEnemyLibrary(EnemyLibrary enemyLibrary) {
        this.enemyLibrary = enemyLibrary;
    }

    public ShopLibrary getShopLibrary() {
        return shopLibrary;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public MapLibrary getMapLibrary() {
        return mapLibrary;
    }

    public EnemyLibrary getEnemyLibrary() {
        return enemyLibrary;
    }

    public ShopDaoImpl getShopDao() {
        return shopDao;
    }

    public PlayerDaoImpl getPlayerDao() {
        return playerDao;
    }

    public EnemyDaoImpl getEnemyDao() {
        return enemyDao;
    }

    public MapDaoImpl getMapDao() {
        return mapDao;
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
     * @param gameService
     */
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * 获取游戏服务对象
     *
     * @return
     */
    public GameService getGameService() {
        return gameService;
    }

    /**
     * 销毁初始化服务
     */
    public void destroyGameService() {
        if (gameService != null) {
            gameService.stopSelf();
            gameService = null;
        }
    }

    /**
     * 关闭数据库
     */
    private void closeDatabase() {
        Log.e(TAG, "closeDatabase");
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
