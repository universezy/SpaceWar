package com.example.agentzengyu.spacewar.entity.single;

/**
 * Created by Agent ZengYu on 2017/7/20.
 */

import com.example.agentzengyu.spacewar.tool.MD5Util;

import java.io.Serializable;

/**
 * 关联
 */
public class Relevancy implements Serializable {
    private String mapName = "";
    private String enemyName = "";
    private int enemyCount = 0;

    private Relevancy() {

    }

    public Relevancy(String mapName, String enemyName, int enemyCount) {
        this.mapName = mapName;
        this.enemyName = enemyName;
        this.enemyCount = enemyCount;
    }

    public String getMapName() {
        return mapName;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public String getMD5() {
        return MD5Util.getMD5FromString(mapName + enemyName);
    }
}
