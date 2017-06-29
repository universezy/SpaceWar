package com.example.agentzengyu.spacewar.application;

import android.app.Application;
import android.util.Log;

import com.example.agentzengyu.spacewar.entity.set.EnemyLibrary;
import com.example.agentzengyu.spacewar.entity.set.MapLibrary;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.set.ShopLibrary;
import com.example.agentzengyu.spacewar.service.GameService;
import com.example.agentzengyu.spacewar.service.InitService;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

/**
 * 应用类，全局管理
 */
public class SpaceWarApp extends Application {
    private InitService initService = null;
    private GameService gameService = null;
    private ShopLibrary shopLibrary = new ShopLibrary();
    private PlayerData playerData = new PlayerData();
    private MapLibrary mapLibrary = new MapLibrary();
    private EnemyLibrary enemyLibrary = new EnemyLibrary();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("SpaceWarApp", "onCreate.");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e("SpaceWarApp", "onTerminate.");
        if (initService != null) {
            initService.stopSelf();
        }
        if (gameService != null) {
            gameService.stopSelf();
        }
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
     * @param initService
     */
    public void setInitService(InitService initService) {
        this.initService = initService;
    }

    /**
     * 获取初始化服务对象
     *
     * @return
     */
    public InitService getInitService() {
        return initService;
    }

    /**
     * 销毁初始化服务
     */
    public void destroyInitService(){
        this.initService.stopSelf();
        this.initService = null;
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
    public void destroyGameService(){
        this.gameService.stopSelf();
        this.gameService = null;
    }
}
