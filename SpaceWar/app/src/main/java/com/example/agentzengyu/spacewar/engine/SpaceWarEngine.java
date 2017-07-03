package com.example.agentzengyu.spacewar.engine;

import android.content.Context;
import android.os.Handler;

import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.EnemyItem;
import com.example.agentzengyu.spacewar.entity.single.MapItem;

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
public class SpaceWarEngine implements IStatus {
    private static SpaceWarEngine instance = null;
    private IEngine engine = null;
    private Context context = null;
    private Handler mapHandler = new Handler();
    private Runnable mapRunnable = null;
    private MusicPlayer musicPlayer = null;

    //数据镜像
    private PlayerData playerMirror = null;
    private ArrayList<EnemyItem> enemysMirror = null;
    private ArrayList mapMirror = null;

    /**
     * 私有构造初始化变量
     */
    private SpaceWarEngine(Context context) {
        mapRunnable = new Runnable() {
            @Override
            public void run() {
                updateMap();
                mapHandler.postDelayed(mapRunnable, 100);
            }
        };
        musicPlayer = MusicPlayer.getInstance(context);
    }

    /**
     * 单例模式只允许一个引擎实例存在
     *
     * @return 引擎实例
     */
    public static SpaceWarEngine getInstance(Context context) {
        if (instance == null) {
            synchronized (SpaceWarEngine.class) {
                if (instance == null) {
                    instance = new SpaceWarEngine(context);
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
     * 加载资源
     *
     * @param playerSource 玩家资源
     * @param mapItem      地图对象
     */
    public void loadSource(PlayerData playerSource, MapItem mapItem) {
        loadMirror(playerSource, mapItem.getEnemys(), mapItem.getMapSource());
        loadMusic(mapItem.getMusic());
    }

    /**
     * * 加载镜像
     *
     * @param playerSource 玩家资源
     * @param enemySource  敌人资源
     * @param mapSource    地图资源
     */
    private void loadMirror(PlayerData playerSource, ArrayList<EnemyItem> enemySource, ArrayList mapSource) {
        playerMirror = null;
        playerMirror = (PlayerData) createMirror(playerSource);
        engine.notifyInitMsg("Loading player data successful.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enemysMirror = null;
        enemysMirror = (ArrayList<EnemyItem>) createMirror(enemySource);
        engine.notifyInitMsg("Loading enemy data successful.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mapMirror = null;
        mapMirror = (ArrayList) createMirror(mapSource);
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

    /**
     * 加载音乐
     *
     * @param musicSource
     */
    private void loadMusic(int musicSource) {
        musicPlayer.init(musicSource);
    }

    /**
     * 更新地图
     */
    private void updateMap(/**/) {
        //TODO
        engine.updateMap(/**/);
    }

    @Override
    public void onStart() {
        mapHandler.postDelayed(mapRunnable, 100);
        musicPlayer.onStart();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onContinue() {

    }

    @Override
    public void onStop() {
        mapHandler.removeCallbacks(mapRunnable);
    }
}
