package com.example.agentzengyu.spacewar.application;

import android.app.Application;
import android.util.Log;

import com.example.agentzengyu.spacewar.entity.PlayerData;
import com.example.agentzengyu.spacewar.entity.ShopData;
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
    private PlayerData playerData = new PlayerData();
    private ShopData shopData = new ShopData();

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

    public PlayerData getPlayerData() {
        return playerData;
    }

    public ShopData getShopData() {
        return shopData;
    }

    /**
     * 设置服务
     *
     * @param initService
     */
    public void setInitService(InitService initService) {
        this.initService = initService;
    }

    /**
     * 获取服务对象
     *
     * @return
     */
    public InitService getInitService() {
        return initService;
    }

    /**
     * 设置服务
     *
     * @param gameService
     */
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * 获取服务对象
     *
     * @return
     */
    public GameService getGameService() {
        return gameService;
    }
}
