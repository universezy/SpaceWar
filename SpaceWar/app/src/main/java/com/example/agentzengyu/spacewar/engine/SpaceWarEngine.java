package com.example.agentzengyu.spacewar.engine;

import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.EnemyItem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 游戏引擎
 */
public class SpaceWarEngine implements IStatus{
    private static SpaceWarEngine instance = null;
    private IEngine engine = null;

    //数据镜像
    private PlayerData playerMirror = null;
    private ArrayList<EnemyItem> enemysMirror  =null;

    private SpaceWarEngine() {

    }

    /**
     * 单例模式只允许一个引擎实例存在
     *
     * @return
     */
    public static SpaceWarEngine getInstance() {
        if (instance == null) {
            synchronized (SpaceWarEngine.class) {
                if (instance == null) {
                    instance = new SpaceWarEngine();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化消息回调
     *
     * @param engine 引擎接口
     */
    public void initMsgCallBack(IEngine engine) {
        if (this.engine == null) {
            this.engine = engine;
        }
    }

    /**
     * 加载镜像，每一个回合前必须调用
     */
    public void loadMirror(PlayerData playerSource, ArrayList<EnemyItem> enemys) {
        playerMirror = null;
        playerMirror = (PlayerData) createMirror(playerSource);
        engine.notifyInitMsg("Loading player data successful.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enemysMirror = null;
        enemysMirror = (ArrayList<EnemyItem>) createMirror(enemys);
        engine.notifyInitMsg("Loading map data successful.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建镜像
     *
     * @param source 数据源
     * @return 镜像
     */
    private Object createMirror(Object source) {
        Object mirror = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(source);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            mirror = objectInputStream.readObject();

            objectInputStream.close();
            byteArrayInputStream.close();
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mirror;
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onContinue() {

    }

    @Override
    public void onStop() {

    }
}
