package com.example.agentzengyu.spacewar.engine;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.basic.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.basic.single.Level;
import com.example.agentzengyu.spacewar.view.GameSurfaceView;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 游戏引擎
 */
public class SpaceWarEngine implements IStatus, SensorEventListener {
    private static String TAG = "";
    //引擎实例
    private static SpaceWarEngine instance = null;
    //应用管理
    private SpaceWarApp app = null;
    //游戏界面
    private GameSurfaceView gameSurfaceView;
    //消息接口
    private IMessage iMessage = null;
    //传感器管理
    private SensorManager sensorManager;
    //传感器
    private Sensor sensor;
    //音乐播放器
    private MusicPlayer musicPlayer;
    //数据镜像
    private PlayerData playerMirror;
    private Level levelMirror;
    //实时重力传感器坐标
    private float GX = 0, GY = 0, GZ = 0;
    //初始重力传感器坐标
    private float SX = 0, SY = 0;
    //开始初始化重力传感器
    private boolean initGravitySensor = false;
    //开始监听重力传感器
    private boolean listenGravitySensor = false;

    /**
     * 私有构造初始化变量
     */
    private SpaceWarEngine(Context context) {
        app = (SpaceWarApp) context.getApplicationContext();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
     * @param iMessage 消息回调
     */
    public void initEngineCallBack(IMessage iMessage) {
        if (this.iMessage == null && iMessage != null) {
            this.iMessage = iMessage;
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
        gameSurfaceView.init(playerMirror, levelMirror);
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

    public void updatePlayerCoord(float X, float Y) {
        //standard: x=8.0, y=0.0, offset=2
        float acceleratedX, acceleratedY;
        float deltaX = X - SX;
        float deltaY = Y - SY;

        if (deltaX < -2) {
            acceleratedX = -0.2f;
        } else if (deltaX > 2) {
            acceleratedX = 0.2f;
        } else if (Math.abs(deltaX) < 0.5) {
            acceleratedX = 0.0f;
        } else {
            acceleratedX = deltaX / 10;
        }

        if (deltaY < -2) {
            acceleratedY = -0.2f;
        } else if (deltaY > 2) {
            acceleratedY = 0.2f;
        } else if (Math.abs(deltaY) < 0.5) {
            acceleratedY = 0.0f;
        } else {
            acceleratedY = deltaY / 10;
        }

        gameSurfaceView.setAccelerated(acceleratedX, acceleratedY);
    }

    /**
     * 重置
     */
    private void reset() {
        initGravitySensor = false;
        listenGravitySensor = false;
    }

    /********************************* IStatus *********************************/
    @Override
    public void onPrepare(final Level level, GameSurfaceView gameSurfaceView) {
        Log.e(TAG, "onPrepare");
        this.gameSurfaceView = gameSurfaceView;
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
        gameSurfaceView.startGame();
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
        gameSurfaceView.stopGame();
    }
}
