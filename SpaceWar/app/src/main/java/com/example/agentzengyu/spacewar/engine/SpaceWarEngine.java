package com.example.agentzengyu.spacewar.engine;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;

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
public class SpaceWarEngine implements IStatus, SensorEventListener {
    private static String TAG = "";
    private static SpaceWarEngine instance = null;
    //引擎接口
    private IEngine engine = null;
    private Context context = null;
    //传感器管理
    private SensorManager sensorManager = null;
    //传感器
    private Sensor sensor = null;
    //地图处理器
    private Handler mapHandler = new Handler();
    //玩家处理器
    private Handler playerHandler = new Handler();
    //地图子线程
    private Runnable mapRunnable = null;
    //玩家子线程
    private Runnable playerRunnable = null;
    //音乐播放器
    private MusicPlayer musicPlayer = null;

    //数据镜像
    private PlayerData playerMirror = null;
    private ArrayList<EnemyItem> enemysMirror = null;
    private ArrayList mapMirror = null;
    //重力传感器坐标
    private float GX = 0, GY = 0, GZ = 0;

    /**
     * 私有构造初始化变量
     */
    private SpaceWarEngine(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mapRunnable = new Runnable() {
            @Override
            public void run() {
                updateMap();
                mapHandler.postDelayed(mapRunnable, 100);
            }
        };
        playerRunnable = new Runnable() {
            @Override
            public void run() {
                updatePlayer();
                playerHandler.postDelayed(playerRunnable, 1000);
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
        TAG = instance.getClass().getName();
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
    public void prepare(PlayerData playerSource, MapItem mapItem) {
//        loadMirror(playerSource, mapItem.getEnemys(), mapItem.getMapSource());
//        loadMusic(mapItem.getMusic());
        initGravitySensorCoord();
    }

    /**
     * 加载镜像
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
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mirror;
    }

    /**
     * 加载音乐
     *
     * @param musicSource 音乐资源
     */
    private void loadMusic(int musicSource) {
        String msg = musicPlayer.init(musicSource);
        engine.notifyInitMsg(msg);
    }

    /**
     * 初始化重力感应器坐标
     */
    private void initGravitySensorCoord() {
        while (!isPrepared(GX, GY, GZ)) {
            engine.notifyInitMsg("Please slant the screen with an angle of 45 degrees on the horizontal.");
        }
    }

    /**
     * 更新地图
     */
    private void updateMap(/**/) {
        //TODO
        engine.updateMap(/**/);
    }

    /**
     * 更新玩家
     */
    private void updatePlayer() {
        String direction = null;
        String shieldStatus = null;
        boolean destroy = false;

        engine.updatePlayer(direction, shieldStatus, destroy);
    }

    /**
     * 设备坐标是否合适
     *
     * @param gx 传入的x坐标
     * @param gy 传入的y坐标
     * @param gz 传入的z坐标
     * @return
     */
    private boolean isPrepared(float gx, float gy, float gz) {
        long startTime = System.currentTimeMillis();
        if (gz < 0) return false;
        if (gy < -1 || gy > 1) return false;
        if (gx < 3 || gx > 7) return false;
        long endTime = System.currentTimeMillis();
        long deltaTime = endTime - startTime;
        float deltaX = GX - gx;
        float deltaY = GY - gy;
        float deltaZ = GZ - gz;
        if (deltaX * 1000 / deltaTime < 1 &&
                deltaY * 1000 / deltaTime < 1 &&
                deltaZ * 1000 / deltaTime < 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart.");
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);//SensorManager.SENSOR_DELAY_GAME
//        mapHandler.postDelayed(mapRunnable, 100);
//        playerHandler.postDelayed(playerRunnable, 1000);
//        musicPlayer.onStart();
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause.");

    }

    @Override
    public void onContinue() {
        Log.e(TAG, "onContinue.");

    }

    @Override
    public void onStop() {
        Log.e(TAG, "onStop.");
        sensorManager.unregisterListener(this);
        mapHandler.removeCallbacks(mapRunnable);
        playerHandler.removeCallbacks(playerRunnable);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        GX = event.values[SensorManager.DATA_X];
        GY = event.values[SensorManager.DATA_Y];
        GZ = event.values[SensorManager.DATA_Z];
        Log.e(TAG, "GX>>> " + GX);
        Log.e(TAG, "GY>>> " + GY);
        Log.e(TAG, "GZ>>> " + GZ);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
