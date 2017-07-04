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

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 游戏引擎
 */
public class SpaceWarEngine implements IStatusToDo, IGameToDo, SensorEventListener {
    private static String TAG = "";
    //引擎实例
    private static SpaceWarEngine instance = null;
    private SpaceWarApp app = null;
    //消息接口
    private IMessageCallBack iMessageCallBack = null;
    //游戏接口
    private IGameCallBack iGameCallBack = null;
    //传感器管理
    private SensorManager sensorManager = null;
    //传感器
    private Sensor sensor = null;
    //地图处理器
    private Handler mapHandler = new Handler();
    //地图子线程
    private Runnable mapRunnable = null;
    //音乐播放器
    private MusicPlayer musicPlayer = null;
    //玩家子弹处理器
    private Handler playerHandler = new Handler();
    //玩家子弹子线程
    private Runnable playerRunnable = null;
    //敌人子弹处理器
    private Handler enemyHandler = new Handler();
    //敌人子弹子线程
    private Runnable enemyRunnable = null;
    //数据镜像
    private PlayerData playerMirror = null;
    private ArrayList<EnemyItem> enemysMirror = null;
    private ArrayList mapMirror = null;
    //玩家子弹速度
    private float playerSpeed = 0;
    //玩家移动速度
    private float playerAgility = 0;
    //实时重力传感器坐标
    private float GX = 0, GY = 0, GZ = 0;
    //初始重力传感器坐标
    private float SX = 0, SY = 0, SZ = 0;
    //开始初始化重力传感器
    private boolean init = false;
    //开始监听重力传感器
    private boolean listen = false;
    //玩家坐标
    private float playerX = 500, playerY = 1000;
    //移动值比例
    private final static int SCALE = 20;
    //子弹数组
    private ArrayList<Bullet> bulletsPlayer = new ArrayList<>();
    private ArrayList<Bullet> bulletsEnemy = new ArrayList<>();

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
     * @param iMessageCallBack 引擎接口
     */
    public void initEngineCallBack(IMessageCallBack iMessageCallBack, IGameCallBack iGameCallBack) {
        if (this.iMessageCallBack == null) {
            this.iMessageCallBack = iMessageCallBack;
        }
        if (this.iGameCallBack == null) {
            this.iGameCallBack = iGameCallBack;
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
        Log.e(TAG, "loadMirror");
        MirrorCreator creator = new MirrorCreator();
        playerMirror = null;
        playerMirror = (PlayerData) creator.create(playerSource);
        playerSpeed = playerMirror.getSpeed().getValue();
        playerAgility = playerMirror.getAgility().getValue();
        iMessageCallBack.notifyInitMsg("Loading player data successful.", false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enemysMirror = null;
        enemysMirror = (ArrayList<EnemyItem>) creator.create(enemySource);
        iMessageCallBack.notifyInitMsg("Loading enemy data successful.", false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mapMirror = null;
        mapMirror = (ArrayList) creator.create(mapSource);
        iMessageCallBack.notifyInitMsg("Loading map data successful.", false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载音乐
     *
     * @param musicSource 音乐资源
     */
    private void loadMusic(final int musicSource) {
        Log.e(TAG, "loadMusic");
        String msg = musicPlayer.init(musicSource);
        iMessageCallBack.notifyInitMsg(msg, false);
    }

    /**
     * 初始化重力感应器坐标
     */
    private void initGravitySensorCoord() {
        Log.e(TAG, "initGravitySensorCoord");
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        init = true;
    }

    /**
     * 更新地图
     */
    private void updateMapLocation(/**/) {
        Log.e(TAG, "updateMapLocation");
        //TODO
    }

    /**
     * 更新玩家
     */
    private void updatePlayerLocation(float X, float Y) {
        Log.e(TAG, "updatePlayerLocation");
        if (X - SX > 0.5) {        //下
            if (playerY != 1000) {
                playerY += playerAgility / SCALE;
                if (playerY > 1000) playerY = 1000;
            }
        } else if (SX - X > 0.5) { //上
            if (playerY != 0) {
                playerY -= playerAgility / SCALE;
                if (playerY < 0) playerY = 0;
            }
        }
        if (Y - SY > 0.5) {        //右
            if (playerX != 1000) {
                playerX += playerAgility / SCALE;
                if (playerX > 1000) playerX = 1000;
            }
        } else if (SY - Y > 0.5) { //左
            if (playerX != 0) {
                playerX -= playerAgility / SCALE;
                if (playerX < 0) playerX = 0;
            }
        }
        iGameCallBack.updatePlayer(playerX, playerY);
    }

    /**
     * 更新玩家子弹坐标
     */
    private void updatePlayerBullets() {
        Log.e(TAG, "updatePlayerBullets");
        //TODO
    }

    /**
     * 更新敌人子弹坐标
     */
    private void updateEnemyBullets() {
        Log.e(TAG, "updateEnemyBullets");
    }

    /**
     * 初始化坐标
     *
     * @param gx 传入的x坐标
     * @param gy 传入的y坐标
     * @param gz 传入的z坐标
     */
    private void initCoord(float gx, float gy, float gz) {
        if (gz > 0 && gx > 5.5 && gx < 6.5 && gy > -0.5 && gy < 0.5) {
            iMessageCallBack.notifyInitMsg("Initializing gravity sensor successful.", true);
            init = false;
            SX = gx;
            SY = gy;
            SZ = gz;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onStart();
            return;
        }
        iMessageCallBack.notifyInitMsg("Please slant the screen with an angle of 45 degrees on the horizontal.", false);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float gx = GX;
        float gy = GY;
        float gz = GZ;
        GX = event.values[SensorManager.DATA_X];
        GY = event.values[SensorManager.DATA_Y];
        GZ = event.values[SensorManager.DATA_Z];
        if (init) {
            initCoord(gx, gy, gz);
        }
        if (listen && GZ > 0) {
            updatePlayerLocation(GX, GY);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /********************************* IStatusToDo *********************************/

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
        }
        ).start();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart");
//        musicPlayer.onStart();
//        mapHandler.postDelayed(mapRunnable, 100);
//        playerHandler.postDelayed(playerRunnable, 200);
//        enemyHandler.postDelayed(enemyRunnable, 200);
        listen = true;
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
        listen = false;
        sensorManager.unregisterListener(this);
//        musicPlayer.onStop();
//        mapHandler.removeCallbacks(mapRunnable);
        playerHandler.removeCallbacks(playerRunnable);
        enemyHandler.removeCallbacks(enemyRunnable);
    }

    /********************************* IGameToDo *********************************/

    @Override
    public void shotEnemy() {
        Log.e(TAG, "shotEnemy");
    }

    @Override
    public void openShield() {
        Log.e(TAG, "openShield");
    }

    @Override
    public void launchBomb() {
        Log.e(TAG, "launchBomb");
    }
}
