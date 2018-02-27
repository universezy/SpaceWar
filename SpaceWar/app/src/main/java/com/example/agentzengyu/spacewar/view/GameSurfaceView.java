package com.example.agentzengyu.spacewar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.agentzengyu.spacewar.entity.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.single.Enemy;
import com.example.agentzengyu.spacewar.entity.single.Level;
import com.example.agentzengyu.spacewar.game.EnemyShip;
import com.example.agentzengyu.spacewar.game.GameComponentFactory;
import com.example.agentzengyu.spacewar.game.PlayerShip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Agent ZengYu on 2017/7/27.
 */

/**
 * 游戏界面视图
 */
public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable, ISurfaceView, IPlayer {
    private final String TAG = getClass().getName();

    private SurfaceHolder holder;
    private Thread thread;
    private Handler handlerShield, handlerLaser, handlerEnemy;
    private Runnable runnableShield, runnableLaser, runnableEnemy;

    private boolean run = true;
    private boolean shield = false;
    private boolean laser = false;
    private int colorText = Color.parseColor("#fffacd");
    private float screenWidth, screenHeight;
    private float backgroundWidth, backgroundHeight;
    private float coordX1 = 0.0f, coordY1 = 0.0f;
    private float coordX2 = 0.0f, coordY2 = 0.0f;
    private float scaleX = 1, scaleY = 1;
    private final static int DELAY_PLAYER = 5000;
    private final static int DELAY_ENEMY = 2000;

    protected Paint paintBackground, paintText;
    protected Canvas canvas;
    private Bitmap bitmapLevel;

    private GameComponentFactory factory;
    private PlayerData playerData;
    private Level level;
    private PlayerShip playerShip;
    private List<EnemyShip> enemyShips = new ArrayList<>();

    public GameSurfaceView(Context context) {
        super(context);
        initDefault();
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefault();
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefault();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG, "surfaceCreated");
        screenWidth = getWidth();
        screenHeight = getHeight();
        Log.e("screenWidth", "" + screenWidth);
        Log.e("screenHeight", "" + screenHeight);new String("");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(TAG, "surfaceDestroyed");
        stopGame();
    }

    @Override
    public void run() {
        while (run) {
            draw();
            onBackgroundAction();
        }
        Log.e(TAG,"thread stop");
    }

    /************************ ISurfaceView ************************/
    @Override
    public void initDefault() {
        paintBackground = new Paint();
        paintBackground.setAntiAlias(true);

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setTextSize(50);
        paintText.setColor(colorText);

        holder = this.getHolder();
        holder.addCallback(this);

        handlerShield = new Handler();
        handlerLaser = new Handler();
        handlerEnemy = new Handler();

        runnableShield = new Runnable() {
            @Override
            public void run() {
                shield = false;
                playerShip.openShield(false);
            }
        };
        runnableLaser = new Runnable() {
            @Override
            public void run() {
                laser = false;
                playerShip.openLaser(false);
            }
        };
        runnableEnemy = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < enemyShips.size(); i++) {
                    enemyShips.get(i).shootPlayer(factory);
                }
                handlerEnemy.postDelayed(runnableEnemy, DELAY_ENEMY);
            }
        };
    }

    @Override
    public void init(PlayerData playerData, Level level) {
        this.playerData = playerData;
        this.level = level;
        factory = new GameComponentFactory(screenWidth, screenHeight, getResources(), playerData);
        thread = new Thread(this);
        initLevelRes();
        initPlayerRes();
        initEnemyRes();
        playerShip.setEnemyShips(enemyShips);
        for (int i = 0; i < enemyShips.size(); i++) {
            enemyShips.get(i).setPlayerShip(playerShip);
        }
    }

    @Override
    public void initLevelRes() {
        bitmapLevel = BitmapFactory.decodeResource(getResources(), level.getImage());
        backgroundWidth = bitmapLevel.getWidth();
        backgroundHeight = bitmapLevel.getHeight();
        scaleX = screenWidth / backgroundWidth;
        scaleY = screenHeight / backgroundHeight;
    }

    @Override
    public void initPlayerRes() {
        playerShip = factory.createPlayerShip();
    }

    @Override
    public void initEnemyRes() {
        Random random = new Random();
        for (int i = 0; i < level.getEnemies().size(); i++) {
            Enemy enemy = level.getEnemies().get(i);
            level.getEnemies().remove(i);
            level.getEnemies().add(random.nextInt(level.getEnemies().size()), enemy);
        }
        for (int i = 0; i < level.getEnemies().size(); i++) {
            enemyShips.add(factory.createEnemyShip(level.getEnemies().get(i), level.getEnemies().size()));
        }
        enemyShips.add(factory.createBossShip(level.getBoss()));
    }

    @Override
    public void onBackgroundAction() {
        coordY1 += 1;
        if (coordY1 > backgroundHeight) {
            coordY1 = 0;
        }
        coordY2 = coordY1 - backgroundHeight;
    }

    @Override
    public void draw() {
        try {
            canvas = holder.lockCanvas();
            //绘制背景
            canvas.drawColor(Color.BLACK);
            canvas.save();
            canvas.scale(scaleX, scaleX);
            canvas.drawBitmap(bitmapLevel, coordX1, coordY1, paintBackground);
            canvas.drawBitmap(bitmapLevel, coordX2, coordY2, paintBackground);
            canvas.restore();

            //绘制玩家飞船
            playerShip.onDraw(canvas);

            //绘制敌人飞船
            for (int i = 0; i < enemyShips.size(); i++) {
                enemyShips.get(i).onDraw(canvas);
                if (enemyShips.get(i).isCrash && enemyShips.get(i).bullets.size() == 0) {
                    enemyShips.remove(i);
                }
            }

            //绘制文字
            canvas.drawText("LV:\t" + level.getLevelName(), 10, 40, paintText);
            canvas.drawText("HP:\t" + (int)playerShip.getLife(), 10, 100, paintText);

            //判断游戏是否结束
            if (enemyShips.size() == 0 || playerShip.getLife() == 0) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopGame();
                        //show
                    }
                }, 2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void startGame() {
        Log.e(TAG, "startGame");
        run = true;
        thread.start();
        handlerEnemy.postDelayed(runnableEnemy, DELAY_ENEMY);
    }

    @Override
    public void stopGame() {
        Log.e(TAG, "stopGame");
        run = false;
        thread.stop();
        handlerShield.removeCallbacks(runnableShield);
        handlerLaser.removeCallbacks(runnableLaser);
        handlerEnemy.removeCallbacks(runnableEnemy);
        if (bitmapLevel != null && !bitmapLevel.isRecycled()) {
            bitmapLevel.recycle();
        }
        if (playerShip != null) {
            playerShip.onDestroy();
        }
        for (int i = 0; i < enemyShips.size(); i++) {
            enemyShips.get(i).onDestroy();
        }
    }


    /************************ IPlayer ************************/
    @Override
    public void setAccelerated(float X, float Y) {
        playerShip.setAccelerated(Y, X);
    }

    @Override
    public void shootEnemy() {
        playerShip.shootEnemy(factory, playerData.getPlayer().getBullet());
    }

    @Override
    public void openShield() {
        if (shield) return;
        shield = true;
        playerShip.openShield(true);
        handlerShield.postDelayed(runnableShield, DELAY_PLAYER);
    }

    @Override
    public void openLaser() {
        if (laser) return;
        laser = true;
        playerShip.openLaser(true);
        handlerLaser.postDelayed(runnableLaser, DELAY_PLAYER);
    }
}
