package com.example.agentzengyu.spacewar.handler;

import com.example.agentzengyu.spacewar.entity.set.Data;
import com.example.agentzengyu.spacewar.entity.set.EnemyData;
import com.example.agentzengyu.spacewar.others.HandlerCallBack;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

/**
 * 敌人数据处理类
 */
public class EnemyHandler extends DataHandler{
    private EnemyData enemyData = null;

    protected EnemyHandler(Data data, File file, InputStream inputStream) {
        super(data, file, inputStream);
        this.enemyData = (EnemyData) data;
    }

    @Override
    public void read(HandlerCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
