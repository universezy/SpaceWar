package com.example.agentzengyu.spacewar.database;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Agent ZengYu on 2017/7/13.
 */

/**
 * 资源映射
 */
public class ResourceMap {
    private static ResourceMap instance = null;
    //商店
    private Map shopImageMap = new HashMap();

    //敌人
    private Map enemyImageMap = new HashMap();
    private Map bossImageMap = new HashMap();

    //地图
    private Map mapIconMap = new HashMap();
    private Map mapMusicMap = new HashMap();

    private ResourceMap() {

    }

    public static ResourceMap getInstance() {
        if (instance == null) {
            synchronized (ResourceMap.class) {
                if (instance == null) {
                    instance = new ResourceMap();
                }
            }
        }
        return instance;
    }

    public Map getShopImageMap() {
        return shopImageMap;
    }

    public Map getEnemyImageMap() {
        return enemyImageMap;
    }

    public Map getBossImageMap() {
        return bossImageMap;
    }

    public Map getMapIconMap() {
        return mapIconMap;
    }

    public Map getMapMusicMap() {
        return mapMusicMap;
    }
}
