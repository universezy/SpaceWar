package com.example.agentzengyu.spacewar.engine;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.Bullet;
import com.example.agentzengyu.spacewar.entity.single.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 游戏引擎
 */
public class SpaceWarEngine implements IStatusHandle, IEventHandle, SensorEventListener {
    private static String TAG = "";
    //引擎实例
    private static SpaceWarEngine instance = null;
    //应用管理
    private SpaceWarApp app = null;
    //消息接口
    private IMessageCallback iMessageCallback = null;
    //游戏接口
    private IEventCallback iEventCallback = null;
    //传感器管理
    private SensorManager sensorManager = null;
    //传感器
    private Sensor sensor = null;
    //音乐播放器
    private MusicPlayer musicPlayer = null;
    //地图处理器
    private Handler levelHandler = new Handler();
    //地图子线程
    private Runnable levelRunnable = null;
    //玩家子弹处理器
    private Handler playerHandler = new Handler();
    //玩家子弹子线程
    private Runnable playerRunnable = null;
    //敌人子弹处理器
    private Handler enemyHandler = new Handler();
    //敌人子弹子线程
    private Runnable enemyRunnable = null;
    //护盾处理器
    private Handler shieldHandler = new Handler();
    //护盾子线程
    private Runnable shieldRunnable = null;
    //激光处理器
    private Handler laserHandler = new Handler();
    //激光子线程
    private Runnable laserRunnable = null;
    //数据镜像
    private PlayerData playerMirror = null;
    private Level levelMirror = null;
    //子线程刷新延迟
    private int delay = 100;
    //护盾持续时间
    private int shieldKeep = 5;
    //护盾冷却时间
    private int shieldCold = 0;
    //激光持续时间
    private int laserKeep = 3;
    //激光冷却时间
    private int laserCold = 0;
    //实时重力传感器坐标
    private float GX = 0, GY = 0, GZ = 0;
    //初始重力传感器坐标
    private float SX = 0, SY = 0;
    //开始初始化重力传感器
    private boolean initGravitySensor = false;
    //开始监听重力传感器
    private boolean listenGravitySensor = false;
    //玩家坐标
    private float playerX = 500, playerY = 1000;
    //子弹数组
    private List<Bullet> bulletsPlayer = Collections.synchronizedList(new ArrayList<Bullet>());
    private List<Bullet> bulletsEnemy = Collections.synchronizedList(new ArrayList<Bullet>());

    /**
     * 私有构造初始化变量
     */
    private SpaceWarEngine(Context context) {
        if (app == null) {
            app = (SpaceWarApp) context.getApplicationContext();
        }
        if (sensorManager == null) {
            sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        }
        if (sensor == null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if (musicPlayer == null) {
            musicPlayer = MusicPlayer.getInstance(context);
        }
        levelRunnable = new Runnable() {
            @Override
            public void run() {
                updateLevelLocation();
                levelHandler.postDelayed(levelRunnable, delay);
            }
        };
        playerRunnable = new Runnable() {
            @Override
            public void run() {
                updatePlayerBullets();
                playerHandler.postDelayed(playerRunnable, delay);
            }
        };
        enemyRunnable = new Runnable() {
            @Override
            public void run() {
                updateEnemyBullets();
                enemyHandler.postDelayed(enemyRunnable, delay);
            }
        };
        shieldRunnable = new Runnable() {
            @Override
            public void run() {
                setShield();
            }
        };
        laserRunnable = new Runnable() {
            @Override
            public void run() {
                setLaser();
            }
        };
    }

    /**
     * 单例模式只允许一个引擎实例存在
     *
     * @param context 上下文
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
     * 初始化引擎回调
     *
     * @param iMessageCallback 消息回调
     * @param iEventCallback   游戏回调
     */
    public void initEngineCallBack(IMessageCallback iMessageCallback, IEventCallback iEventCallback) {
        if (this.iMessageCallback == null) {
            this.iMessageCallback = iMessageCallback;
        }
        if (this.iEventCallback == null) {
            this.iEventCallback = iEventCallback;
        }
    }

    /**
     * 加载镜像
     *
     * @param playerSource 玩家资源
     * @param levelSource  地图资源
     */
    private void loadMirror(PlayerData playerSource, Level levelSource) {
        playerMirror = null;
        playerMirror = (PlayerData) MirrorBuilder.buildMirror(playerSource);
        shieldCold = playerMirror.getShield().getValue();
        laserCold = playerMirror.getLaser().getValue();
        iMessageCallback.notifyInitMsg("Loading player data successful.", false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        levelMirror = null;
        levelMirror = (Level) MirrorBuilder.buildMirror(levelSource);
        iMessageCallback.notifyInitMsg("Loading enemy data successful.", false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载音乐
     *
     * @param musicSource 音乐资源
     */
    private void loadMusic(int musicSource) {
        String msg = musicPlayer.init(musicSource);
        iMessageCallback.notifyInitMsg(msg, false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化重力感应器坐标
     */
    private void initGravitySensorCoord() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        iMessageCallback.notifyInitMsg("Please slant the screen with an angle about 45 degrees on the horizontal.", false);
        initGravitySensor = true;
    }

    /**
     * 初始化坐标
     *
     * @param gx 传入的x坐标
     * @param gy 传入的y坐标
     * @param gz 传入的z坐标
     */
    private void initCoord(float gx, float gy, float gz) {
        //standard: x=8.0, y=0.0, offset=0.5
        if (gz > 0.0 && gx > 7.5 && gx < 8.5 && gy > -0.5 && gy < 0.5) {
            iMessageCallback.notifyInitMsg("Initializing gravity sensor successful.", true);
            initGravitySensor = false;
            SX = gx;
            SY = gy;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onStart();
            return;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float gx = GX;
        float gy = GY;
        float gz = GZ;
        GX = event.values[SensorManager.DATA_X];
        GY = event.values[SensorManager.DATA_Y];
        GZ = event.values[SensorManager.DATA_Z];
        if (initGravitySensor) {
            initCoord(gx, gy, gz);
        }
        if (listenGravitySensor && GZ > 0) {
            updatePlayerLocation(GX, GY);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * 更新地图
     */
    private void updateLevelLocation(/**/) {
        //TODO
    }

    /**
     * 更新玩家坐标
     *
     * @param X X坐标
     * @param Y Y坐标
     */
    private void updatePlayerLocation(float X, float Y) {
        int velocity = playerMirror.getVelocity().getValue();
        if (X - SX > 0.5) {        //下
            if (playerY != 1000) {
                playerY += velocity;
                if (playerY > 1000) playerY = 1000;
            }
        } else if (SX - X > 0.5) { //上
            if (playerY != 0) {
                playerY -= velocity;
                if (playerY < 0) playerY = 0;
            }
        }
        if (Y - SY > 0.5) {        //右
            if (playerX != 1000) {
                playerX += velocity;
                if (playerX > 1000) playerX = 1000;
            }
        } else if (SY - Y > 0.5) { //左
            if (playerX != 0) {
                playerX -= velocity;
                if (playerX < 0) playerX = 0;
            }
        }
        iEventCallback.updatePlayerLocation(playerX, playerY);
    }

    /**
     * 更新玩家子弹坐标
     */
    private synchronized void updatePlayerBullets() {
        synchronized (bulletsPlayer) {
            for (int i = 0; i < bulletsPlayer.size(); i++) {
                Bullet bullet = bulletsPlayer.get(i);
                bullet.setY(bullet.getY() - bullet.getSpeed());
                if (bullet.getY() < 0)
                    bulletsPlayer.remove(i);
            }
            iEventCallback.updatePlayerBullets(bulletsPlayer);
        }
    }

    /**
     * 更新敌人子弹坐标
     */
    private synchronized void updateEnemyBullets() {
        synchronized (bulletsEnemy) {
            for (int i = 0; i < bulletsEnemy.size(); i++) {
                Bullet bullet = bulletsEnemy.get(i);
                bullet.setY(bullet.getY() - bullet.getSpeed());
                if (bullet.getY() < 0)
                    bulletsEnemy.remove(i);
            }
            iEventCallback.updateEnemyBullets(bulletsEnemy);
        }
    }

    /**
     * 设置护盾
     */
    private void setShield() {
        if (shieldCold != 0) {
            if (shieldKeep == 0) {
                iEventCallback.setShield(false, shieldCold);
            } else {
                iEventCallback.setShield(true, shieldCold);
                shieldKeep--;
            }
            shieldCold--;
            shieldHandler.postDelayed(shieldRunnable, 1000);
        } else {
            shieldCold = playerMirror.getShield().getValue();
            shieldKeep = 5;
            iEventCallback.setShield(false, shieldCold);
        }
    }

    /**
     * 设置激光
     */
    private void setLaser() {
        if (laserCold != 0) {
            if (laserKeep == 0) {
                iEventCallback.setLaser(false, laserCold);
            } else {
                iEventCallback.setLaser(true, laserCold);
                laserKeep--;
            }
            laserCold--;
            laserHandler.postDelayed(laserRunnable, 1000);
        } else {
            laserCold = playerMirror.getLaser().getValue();
            laserKeep = 3;
            iEventCallback.setLaser(false, laserCold);
        }
    }

    /**
     * 重置
     */
    private void reset() {
        initGravitySensor = false;
        listenGravitySensor = false;
        playerX = 500;
        playerY = 1000;
        bulletsPlayer.clear();
        bulletsEnemy.clear();
        shieldKeep = 5;
        laserKeep = 3;
        if (playerMirror == null) return;
        shieldCold = playerMirror.getShield().getValue();
        laserCold = playerMirror.getLaser().getValue();
    }

    /********************************* IStatusHandle *********************************/
    @Override
    public void onPrepare(final Level level) {
        Log.e(TAG, "onPrepare");
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadMirror(app.getPlayerData(), level);
                loadMusic(level.getMusic());
                initGravitySensorCoord();
            }
        }).start();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart");
        reset();
//        musicPlayer.onStart();
//        levelHandler.postDelayed(levelRunnable, delay);
        playerHandler.postDelayed(playerRunnable, delay);
//        enemyHandler.postDelayed(enemyRunnable, delay);
        listenGravitySensor = true;
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause");

    }

    @Override
    public void onContinue() {
        Log.e(TAG, "onContinue");

    }

    @Override
    public void onStop() {
        Log.e(TAG, "onStop");
        sensorManager.unregisterListener(this);
//        musicPlayer.onStop();
//        levelHandler.removeCallbacks(levelRunnable);
        playerHandler.removeCallbacks(playerRunnable);
//        enemyHandler.removeCallbacks(enemyRunnable);
        shieldHandler.removeCallbacks(shieldRunnable);
        laserHandler.removeCallbacks(laserRunnable);
    }

    /********************************* IEventHandle *********************************/
    @Override
    public synchronized void shotEnemy() {
        Bullet bullet = new Bullet(playerX, playerY, playerMirror.getPower().getValue(), playerMirror.getRange().getValue(), playerMirror.getSpeed().getValue());
        synchronized (bulletsPlayer) {
            bulletsPlayer.add(bullet);
        }
    }

    @Override
    public void openShield() {
        if (shieldCold != playerMirror.getShield().getValue()) return;
        shieldHandler.post(shieldRunnable);
    }

    @Override
    public void launchLaser() {
        if (laserCold != playerMirror.getLaser().getValue()) return;
        laserHandler.post(laserRunnable);
    }
}
