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
import com.example.agentzengyu.spacewar.entity.single.Enemy;
import com.example.agentzengyu.spacewar.entity.single.Level;
import com.example.agentzengyu.spacewar.service.IGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 游戏引擎
 */
public class SpaceWarEngine implements IStatus, IPlayer, IEvent, SensorEventListener {
    private static String TAG = "";
    //引擎实例
    private static SpaceWarEngine instance = null;
    //应用管理
    private SpaceWarApp app = null;
    //消息接口
    private IMessage iMessage = null;
    //游戏接口
    private IGame iGame = null;
    //传感器管理
    private SensorManager sensorManager;
    //传感器
    private Sensor sensor;
    //音乐播放器
    private MusicPlayer musicPlayer;

    //地图坐标处理器
    private Handler hCoordLevel;
    //敌人坐标处理器
    private Handler hCoordEnemy;
    //玩家子弹处理器
    private Handler hBulletPlayer;
    //敌人子弹处理器
    private Handler hBulletEnemy;
    //护盾处理器
    private Handler hShield;
    //激光处理器
    private Handler hLaser;

    //地图坐标子线程
    private Runnable rCoordLevel;
    //敌人坐标子线程
    private Runnable rCoordEnemy;
    //玩家子弹子线程
    private Runnable rBulletPlayer;
    //敌人子弹子线程
    private Runnable rBulletEnemy;
    //护盾子线程
    private Runnable rShield;
    //激光子线程
    private Runnable rLaser;

    //数据镜像
    private PlayerData playerMirror;
    private Level levelMirror;
    //子线程刷新延迟
    private final static int DELAY = 100;
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
    //敌人数组
    private List<Enemy> enemies = new ArrayList<>();

    /**
     * 私有构造初始化变量
     */
    private SpaceWarEngine(Context context) {
        app = (SpaceWarApp) context.getApplicationContext();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        musicPlayer = MusicPlayer.getInstance(context);

        hCoordLevel = new Handler();
        hCoordEnemy = new Handler();
        hBulletPlayer = new Handler();
        hBulletEnemy = new Handler();
        hShield = new Handler();
        hLaser = new Handler();

        rCoordLevel = new Runnable() {
            @Override
            public void run() {
                updateLevelCoord();
                hCoordLevel.postDelayed(rCoordLevel, DELAY);
            }
        };
        rCoordEnemy = new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        };
        rBulletEnemy = new Runnable() {
            @Override
            public void run() {
                updateEnemyBullets();
                hBulletEnemy.postDelayed(rBulletEnemy, DELAY);
            }
        };
        rBulletPlayer = new Runnable() {
            @Override
            public void run() {
                updatePlayerBullets();
                hBulletPlayer.postDelayed(rBulletPlayer, DELAY);
            }
        };
        rShield = new Runnable() {
            @Override
            public void run() {
                setShield();
            }
        };
        rLaser = new Runnable() {
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
     * @param iMessage 消息回调
     * @param iGame    游戏回调
     */
    public void initEngineCallBack(IMessage iMessage, IGame iGame) {
        if (this.iMessage == null && iMessage != null) {
            this.iMessage = iMessage;
        }
        if (this.iGame == null && iGame != null) {
            this.iGame = iGame;
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
        iMessage.notifyInitMsg("Loading player data successful.", false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        levelMirror = null;
        levelMirror = (Level) MirrorBuilder.buildMirror(levelSource);
        iMessage.notifyInitMsg("Loading map data successful.", false);
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
        iMessage.notifyInitMsg(msg, false);
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
        iMessage.notifyInitMsg("Please slant the screen with an angle about 45 degrees on the horizontal.", false);
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
            iMessage.notifyInitMsg("Initializing gravity sensor successful.", true);
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
            updatePlayerCoord(GX, GY);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /********************************* IEvent *********************************/
    @Override
    public void updateLevelCoord() {


        iGame.updateLevelCoord();
    }

    @Override
    public void updateEnemyCoord() {


        iGame.updateEnemyCoord();
    }

    @Override
    public void updatePlayerCoord(float X, float Y) {
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
        iGame.updatePlayerCoord(playerX, playerY);
    }

    @Override
    public synchronized void updatePlayerBullets() {
        synchronized (bulletsPlayer) {
            for (int i = 0; i < bulletsPlayer.size(); i++) {
                Bullet bullet = bulletsPlayer.get(i);
                bullet.setY(bullet.getY() - bullet.getSpeed());
                if (bullet.getY() < 0)
                    bulletsPlayer.remove(i);
            }
            iGame.updatePlayerBullets(bulletsPlayer);
        }
    }

    @Override
    public synchronized void updateEnemyBullets() {
        synchronized (bulletsEnemy) {
            for (int i = 0; i < bulletsEnemy.size(); i++) {
                Bullet bullet = bulletsEnemy.get(i);
                bullet.setY(bullet.getY() + bullet.getSpeed());
                if (bullet.getY() > 1000)
                    bulletsEnemy.remove(i);
            }
            iGame.updateEnemyBullets(bulletsEnemy);
        }
    }

    @Override
    public void setShield() {
        if (shieldCold != 0) {
            if (shieldKeep == 0) {
                iGame.setShield(false, shieldCold);
            } else {
                iGame.setShield(true, shieldCold);
                shieldKeep--;
            }
            shieldCold--;
            hShield.postDelayed(rShield, 1000);
        } else {
            shieldCold = playerMirror.getShield().getValue();
            shieldKeep = 5;
            iGame.setShield(false, shieldCold);
        }
    }

    @Override
    public void setLaser() {
        if (laserCold != 0) {
            if (laserKeep == 0) {
                iGame.setLaser(false, laserCold);
            } else {
                iGame.setLaser(true, laserCold);
                laserKeep--;
            }
            laserCold--;
            hLaser.postDelayed(rLaser, 1000);
        } else {
            laserCold = playerMirror.getLaser().getValue();
            laserKeep = 3;
            iGame.setLaser(false, laserCold);
        }
    }

    @Override
    public void destroyPlayer() {


        iGame.destroyPlayer();
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

    /********************************* IStatus *********************************/
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
//        hCoordLevel.postDelayed(rCoordLevel, DELAY);
//        hCoordEnemy.postDelayed(rCoordLevel, DELAY);
        hBulletPlayer.postDelayed(rBulletPlayer, DELAY);
//        hBulletEnemy.postDelayed(rBulletEnemy, DELAY);
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
//        hCoordLevel.removeCallbacks(rCoordLevel);
        hCoordEnemy.removeCallbacks(rCoordEnemy);
        hBulletPlayer.removeCallbacks(rBulletPlayer);
//        hBulletEnemy.removeCallbacks(rBulletEnemy);
        hShield.removeCallbacks(rShield);
        hLaser.removeCallbacks(rLaser);
    }

    /********************************* IPlayer *********************************/
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
        hShield.post(rShield);
    }

    @Override
    public void launchLaser() {
        if (laserCold != playerMirror.getLaser().getValue()) return;
        hLaser.post(rLaser);
    }
}
