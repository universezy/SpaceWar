package com.example.agentzengyu.spacewar.entity.set;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import com.example.agentzengyu.spacewar.entity.single.Enemy;

import java.io.Serializable;
import java.util.Map;

/**
 * 敌人库
 */
public class EnemyLibrary implements Serializable {
    private Map<String, Enemy> normalEnemys = null;
    private Map<String, Enemy> bossEnemys = null;

    public EnemyLibrary() {

    }

    public boolean setNormalEmenys(Map<String, Enemy> normalEmenys) {
        if (normalEmenys == null) {
            return false;
        } else {
            this.normalEnemys = normalEmenys;
            return true;
        }
    }

    public boolean setBossEmenys(Map<String, Enemy> bossEmenys) {
        if (bossEmenys == null) {
            return false;
        } else {
            this.bossEnemys = bossEmenys;
            return true;
        }
    }

    public Map<String, Enemy> getNormalEnemys() {
        return normalEnemys;
    }

    public Map<String, Enemy> getBossEnemys() {
        return bossEnemys;
    }
}
