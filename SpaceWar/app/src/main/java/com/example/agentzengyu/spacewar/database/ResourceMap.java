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
    //商店
    private Map shopMap = new HashMap();

    //敌人
    private Map enemyImageMap = new HashMap();
    private Map bossImageMap = new HashMap();

    //地图
    private Map mapIconMap = new HashMap();
    private Map mapMusicMap = new HashMap();

    public Map getShopMap() {
        return shopMap;
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
