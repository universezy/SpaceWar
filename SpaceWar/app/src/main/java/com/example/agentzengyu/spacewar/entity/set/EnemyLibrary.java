package com.example.agentzengyu.spacewar.entity.set;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

import com.example.agentzengyu.spacewar.entity.single.EnemyItem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 敌人库
 */
public class EnemyLibrary extends AbstractLibrary implements Serializable {
    private ArrayList<EnemyItem> emenys = new ArrayList<>();

    public ArrayList<EnemyItem> getEmenys() {
        return emenys;
    }
}
