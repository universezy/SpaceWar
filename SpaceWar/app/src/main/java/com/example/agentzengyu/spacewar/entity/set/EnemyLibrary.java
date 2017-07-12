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
public class EnemyLibrary extends AbstractLibrary implements Serializable {
    private List<EnemyItem> normalEmenys = new ArrayList<>();
    private List<EnemyItem> bossEmenys = new ArrayList<>();

    public List<EnemyItem> getNormalEmenys() {
        return normalEmenys;
    }

    public List<EnemyItem> getBossEmenys() {
        return bossEmenys;
    }
}
