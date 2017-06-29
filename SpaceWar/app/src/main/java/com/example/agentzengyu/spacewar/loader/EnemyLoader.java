package com.example.agentzengyu.spacewar.loader;

import com.example.agentzengyu.spacewar.entity.set.AbstractLibrary;
import com.example.agentzengyu.spacewar.entity.set.EnemyLibrary;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Agent ZengYu on 2017/6/28.
 */

/**
 * 敌人加载器
 */
public class EnemyLoader extends AbstractLoader {
    private EnemyLibrary enemyLibrary = null;

    protected EnemyLoader(AbstractLibrary abstractLibrary, File file, InputStream inputStream) {
        super(abstractLibrary, file, inputStream);
        this.enemyLibrary = (EnemyLibrary) abstractLibrary;
    }

    @Override
    public void read(ILoader callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
