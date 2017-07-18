package com.example.agentzengyu.spacewar.entity.set;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import com.example.agentzengyu.spacewar.entity.single.EnemyItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 敌人库
 */
public class EnemyLibrary implements Serializable {
    private List<EnemyItem> normalEnemys = null;
    private List<EnemyItem> bossEnemys = null;

    public EnemyLibrary() {

    }

    public boolean setNormalEmenys(List<EnemyItem> normalEmenys) {
        if (normalEmenys == null) {
            return false;
        } else {
            this.normalEnemys = normalEmenys;
            return true;
        }
    }

    public boolean setBossEmenys(List<EnemyItem> bossEmenys) {
        if (bossEmenys == null) {
            return false;
        } else {
            this.bossEnemys = bossEmenys;
            return true;
        }
    }

    public List<EnemyItem> getNormalEnemys() {
        return normalEnemys;
    }

    public List<EnemyItem> getBossEnemys() {
        return bossEnemys;
    }
}
