package com.example.agentzengyu.spacewar.application;

import android.app.Application;
import android.util.Log;

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
    private LoaderService loaderService = null;
    private GameService gameService = null;
    private ShopLibrary shopLibrary = null;
    private PlayerData playerData = null;
    private MapLibrary mapLibrary = null;
    private EnemyLibrary enemyLibrary = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("SpaceWarApp", "onCreate.");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e("SpaceWarApp", "onTerminate.");
        destroyInitService();
        destroyGameService();
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
}
