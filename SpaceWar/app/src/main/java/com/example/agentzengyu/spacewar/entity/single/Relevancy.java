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
    //地图名
    private String levelName = "";
    //敌人名
    private String enemyName = "";
    //敌人数量
    private int enemyCount = 0;

    private Relevancy() {

    }

    public Relevancy(String levelName, String enemyName, int enemyCount) {
        this.levelName = levelName;
        this.enemyName = enemyName;
        this.enemyCount = enemyCount;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public String getMD5() {
        return MD5Util.getMD5FromString(levelName + enemyName);
    }
}
