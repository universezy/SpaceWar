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
import com.example.agentzengyu.spacewar.entity.single.EnemyItem;
import com.example.agentzengyu.spacewar.entity.single.MapItem;

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
    private Handler mapHandler = new Handler();
    //地图子线程
    private Runnable mapRunnable = null;
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
    //数据镜像
    private PlayerData playerMirror = null;
    private ArrayList<EnemyItem> enemysMirror = null;
    private ArrayList mapMirror = null;
    //玩家子弹速度
    private float playerSpeed = 0;
    //玩家移动速度
    private float playerAgility = 0;
    //玩家子弹范围
    private float playerRange = 0;
    //护盾持续时间
    private int shieldKeep = 5;
    //护盾冷却时间
    private int shieldCold = 0;
    //炸弹冷却时间
    private int bombCold = 0;
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
    //移动值比例
    private final static int LOCATION_SCALE = 20;
    //速度比例
    private final static int SPEED_SCALE = 3;
    //子弹数组
    private List<Bullet> bulletsPlayer = Collections.synchronizedList(new ArrayList<Bullet>());
    private List<Bullet> bulletsEnemy = Collections.synchronizedList(new ArrayList<Bullet>());

    /**
     * 私有构造初始化变量
     */
    private SpaceWarEngine(Context context) {
        app = (SpaceWarApp) context.getApplicationContext();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mapRunnable = new Runnable() {
            @Override
            public void run() {
                updateMapLocation();
                mapHandler.postDelayed(mapRunnable, 100);
            }
        };
        playerRunnable = new Runnable() {
            @Override
            public void run() {
                updatePlayerBullets();
                playerHandler.postDelayed(playerRunnable, 200);
            }
        };
        enemyRunnable = new Runnable() {
            @Override
            public void run() {
                updateEnemyBullets();
                enemyHandler.postDelayed(enemyRunnable, 200);
            }
        };
        shieldRunnable = new Runnable() {
            @Override
            public void run() {
                setShield();
            }
        };
        musicPlayer = MusicPlayer.getInstance(context);
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
     * @param enemySource  敌人资源
     * @param mapSource    地图资源
     */
    private void loadMirror(PlayerData playerSource, ArrayList<EnemyItem> enemySource, ArrayList mapSource) {
        MirrorCreator creator = new MirrorCreator();
        playerMirror = null;
        playerMirror = (PlayerData) creator.create(playerSource);
        playerSpeed = playerMirror.getSpeed().getValue();
        playerAgility = playerMirror.getAgility().getValue();
        playerRange = playerMirror.getRange().getValue();
        shieldCold = playerMirror.getShield().getValue();
        bombCold = playerMirror.getBomb().getValue();
        iMessageCallback.notifyInitMsg("Loading player data successful.", false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enemysMirror = null;
        enemysMirror = (ArrayList<EnemyItem>) creator.create(enemySource);
        iMessageCallback.notifyInitMsg("Loading enemy data successful.", false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mapMirror = null;
        mapMirror = (ArrayList) creator.create(mapSource);
        iMessageCallback.notifyInitMsg("Loading map data successful.", false);
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
        //standard: x=7.0, y=0.0, offset=0.5
        if (gz > 0.0 && gx > 6.5 && gx < 7.5 && gy > -0.5 && gy < 0.5) {
            iMessageCallback.notifyInitMsg("Initializing gravity sensor successful.", true);
            initGravitySensor = false;
            SX = gx;
            SY = gy;
            try {
                Thread.sleep(500);
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
    private void updateMapLocation(/**/) {
        //TODO
    }

    /**
     * 更新玩家坐标
     *
     * @param X X坐标
     * @param Y Y坐标
     */
    private void updatePlayerLocation(float X, float Y) {
        //TODO upgrade
        if (X - SX > 0.5) {        //下
            if (playerY != 1000) {
                playerY += playerAgility / LOCATION_SCALE;
                if (playerY > 1000) playerY = 1000;
            }
        } else if (SX - X > 0.5) { //上
            if (playerY != 0) {
                playerY -= playerAgility / LOCATION_SCALE;
                if (playerY < 0) playerY = 0;
            }
        }
        if (Y - SY > 0.5) {        //右
            if (playerX != 1000) {
                playerX += playerAgility / LOCATION_SCALE;
                if (playerX > 1000) playerX = 1000;
            }
        } else if (SY - Y > 0.5) { //左
            if (playerX != 0) {
                playerX -= playerAgility / LOCATION_SCALE;
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
                bullet.setY(bullet.getY() - bullet.getSpeed() / SPEED_SCALE);
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
                bullet.setY(bullet.getY() - bullet.getSpeed() / SPEED_SCALE);
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
            shieldKeep = 3;
            iEventCallback.setShield(false, shieldCold);
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
    }

    /********************************* IStatusHandle *********************************/
    @Override
    public void onPrepare(final MapItem mapItem) {
        Log.e(TAG, "onPrepare");
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadMirror(app.getPlayerData(), mapItem.getEnemys(), mapItem.getMapSource());
                loadMusic(mapItem.getMusic());
                initGravitySensorCoord();
            }
        }).start();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart");
//        musicPlayer.onStart();
//        mapHandler.postDelayed(mapRunnable, 100);
        playerHandler.postDelayed(playerRunnable, 200);
//        enemyHandler.postDelayed(enemyRunnable, 200);
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
        reset();
        sensorManager.unregisterListener(this);
//        musicPlayer.onStop();
//        mapHandler.removeCallbacks(mapRunnable);
        playerHandler.removeCallbacks(playerRunnable);
//        enemyHandler.removeCallbacks(enemyRunnable);
        shieldHandler.removeCallbacks(shieldRunnable);

    }

    /********************************* IEventHandle *********************************/
    @Override
    public synchronized void shotEnemy() {
        Bullet bullet = new Bullet(playerX, playerY, playerRange, playerSpeed);
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
    public void launchBomb() {
    }
}
