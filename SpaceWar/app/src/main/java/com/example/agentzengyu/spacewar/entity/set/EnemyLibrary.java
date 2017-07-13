package com.example.agentzengyu.spacewar.entity.set;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import com.example.agentzengyu.spacewar.entity.single.EnemyItem;

import java.io.Serializable;
import java.util.List;

/**
 * 敌人库
 */
public class EnemyLibrary  implements Serializable {
    private List<EnemyItem> normalEmenys = null;
    private List<EnemyItem> bossEmenys = null;

    public void setNormalEmenys(List<EnemyItem> normalEmenys) {
        this.normalEmenys = normalEmenys;
    }

    public void setBossEmenys(List<EnemyItem> bossEmenys) {
        this.bossEmenys = bossEmenys;
    }

    public List<EnemyItem> getNormalEmenys() {
        return normalEmenys;
    }

    public List<EnemyItem> getBossEmenys() {
        return bossEmenys;
    }
}
