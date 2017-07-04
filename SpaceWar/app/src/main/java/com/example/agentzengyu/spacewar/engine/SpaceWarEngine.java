package com.example.agentzengyu.spacewar.engine;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.Constant;
import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.Bullet;
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
    private int playerSpeed = 0;
    //实时重力传感器坐标
    private float GX = 0, GY = 0, GZ = 0;
    //初始重力传感器坐标
    private float SX = 0, SY = 0, SZ = 0;
    //开始初始化重力传感器
    private boolean init = false;
    //开始监听重力传感器
    private boolean listen = false;
    //子弹数组
    private ArrayList<Bullet> bulletsPlayer = new ArrayList<>();
    private ArrayList<Bullet> bulletsEnemy = new ArrayList<>();


    /**
     * 私有构造初始化变量
     */
    private SpaceWarEngine(Context context) {
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
        Log.e(TAG, "prepare.");
//        loadMirror(playerSource, mapItem.getEnemys(), mapItem.getMapSource());
//        loadMusic(mapItem.getMusic());
        initGravitySensorCoord();
        bindViewSource();
    }

    /**
     * 加载镜像
     *
     * @param playerSource 玩家资源
     * @param enemySource  敌人资源
     * @param mapSource    地图资源
     */
    private void loadMirror(PlayerData playerSource, ArrayList<EnemyItem> enemySource, ArrayList mapSource) {
        Log.e(TAG, "loadMirror.");
        playerMirror = null;
        playerMirror = (PlayerData) createMirror(playerSource);
        playerSpeed = playerMirror.getSpeed().getValue();
        engine.notifyInitMsg("Loading player data successful.", false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enemysMirror = null;
        enemysMirror = (ArrayList<EnemyItem>) createMirror(enemySource);
        engine.notifyInitMsg("Loading enemy data successful.", false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mapMirror = null;
        mapMirror = (ArrayList) createMirror(mapSource);
        engine.notifyInitMsg("Loading map data successful.", false);
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
        Log.e(TAG, "loadMusic.");
        String msg = musicPlayer.init(musicSource);
        engine.notifyInitMsg(msg, false);
    }

    /**
     * 初始化重力感应器坐标
     */
    private void initGravitySensorCoord() {
        Log.e(TAG, "initGravitySensorCoord.");
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        init = true;
    }

    /**
     * 更新地图
     */
    private void updateMapLocation(/**/) {
        Log.e(TAG, "updateMapLocation.");
        //TODO
        engine.updateMap(/**/);
    }

    /**
     * 更新玩家
     */
    private void updatePlayerLocation() {
        Log.e(TAG, "updatePlayerLocation.");
        String directionX = null;
        String directionY = null;
        String shieldStatus = null;
        boolean destroy = false;
        if (GX - SX > 0.5) {
            directionX = Constant.Game.Player.BOTTOM;
        } else if (SX - GX > 0.5) {
            directionX = Constant.Game.Player.TOP;
        }
        if (GY - SY > 0.5) {
            directionY = Constant.Game.Player.RIGHT;
        } else if (SY - GY > 0.5) {
            directionY = Constant.Game.Player.LEFT;
        }
        engine.updatePlayer(directionX, directionY, shieldStatus, destroy);
    }

    /**
     * 更新玩家子弹坐标
     */
    private void updatePlayerBullets() {
        synchronized (bulletsPlayer) {
            for (Bullet bullet : bulletsPlayer) {
                bullet.setY(bullet.getY() - 5 * bullet.getSpeed());
                if (bullet.getY() + bullet.getRadius() <= 0) {
                    bulletsPlayer.remove(bullet);
                }
            }
        }
    }

    /**
     * 更新敌人子弹坐标
     */
    private void updateEnemyBullets() {
        synchronized (bulletsEnemy) {
            for (Bullet bullet : bulletsEnemy) {
                bullet.setY(bullet.getY() + 5 * bullet.getSpeed());
                if (bullet.getY() + bullet.getRadius() <= 0) {
                    bulletsEnemy.remove(bullet);
                }
            }
        }
    }

    /**
     * 设备坐标是否合适
     *
     * @param gx 传入的x坐标
     * @param gy 传入的y坐标
     * @param gz 传入的z坐标
     */
    private void isPrepared(float gx, float gy, float gz) {
        if (gz > 0 && gx > 4.5 && gx < 5.5 && gy > -0.5 && gy < 0.5) {
            engine.notifyInitMsg("Initializing gravity sensor successful.", true);
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
        engine.notifyInitMsg("Please slant the screen with an angle of 45 degrees on the horizontal.", false);
    }

    /**
     * 绑定视图资源
     */
    public void bindViewSource() {
        engine.initPlayer(this.bulletsPlayer);
        engine.initEnemy(this.bulletsEnemy);
    }

    public void shotEnemy(float x, float y) {
        Bullet bullet = new Bullet(x, y, playerMirror.getRange().getValue(), playerMirror.getSpeed().getValue());
        bulletsPlayer.add(bullet);
    }

    public void shotPlayer(float x, float y) {

    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart.");
//        musicPlayer.onStart();
//        mapHandler.postDelayed(mapRunnable, 100);
        playerHandler.postDelayed(playerRunnable, 200);
        enemyHandler.postDelayed(enemyRunnable, 200);
        listen = true;
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
        listen = false;
        sensorManager.unregisterListener(this);
//        musicPlayer.onStop();
        mapHandler.removeCallbacks(mapRunnable);
        playerHandler.removeCallbacks(playerRunnable);
        enemyHandler.removeCallbacks(enemyRunnable);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float gx = GX;
        float gy = GY;
        float gz = GZ;
        GX = event.values[SensorManager.DATA_X];
        GY = event.values[SensorManager.DATA_Y];
        GZ = event.values[SensorManager.DATA_Z];
        Log.e(TAG, "GX>>> " + GX);
        Log.e(TAG, "GY>>> " + GY);
        if (init) {
            isPrepared(gx, gy, gz);
        }
        if (listen) {
            updatePlayerLocation();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
