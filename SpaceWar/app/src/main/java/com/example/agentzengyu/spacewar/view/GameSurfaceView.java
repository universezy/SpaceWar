package com.example.agentzengyu.spacewar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.agentzengyu.spacewar.entity.basic.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.basic.single.Enemy;
import com.example.agentzengyu.spacewar.entity.basic.single.Level;
import com.example.agentzengyu.spacewar.entity.game.EnemyShip;
import com.example.agentzengyu.spacewar.entity.game.GameComponentFactory;
import com.example.agentzengyu.spacewar.entity.game.PlayerShip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/7/27.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable, IPlayer {
    private SurfaceHolder holder;
    private Thread thread;
    private Handler handlerShield, handlerLaser;
    private Runnable runnableShield, runnableLaser;
    private boolean run = true;
    private boolean shield = false;
    private boolean laser = false;
    private float screenWidth, screenHeight;
    private float backgroundWidth, backgroundHeight;
    private float coordX1 = 0.0f, coordY1 = 0.0f;
    private float coordX2 = 0.0f, coordY2 = 0.0f;
    private float scaleX = 1, scaleY = 1;
    private final static int DELAY = 5000;
    protected Paint paint;
    protected Canvas canvas;
    private Bitmap bitmapLevel;

    private GameComponentFactory factory;
    private PlayerData playerData;
    private Level level;
    private PlayerShip playerShip;
    private List<EnemyShip> enemyShips = new ArrayList<>();
    private EnemyShip boss;

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
        screenWidth = getWidth();
        screenHeight = getHeight();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopGame();
    }

    private void initDefault() {
        paint = new Paint();
        paint.setAntiAlias(true);


        holder = this.getHolder();
        holder.addCallback(this);

        handlerShield = new Handler();
        handlerLaser = new Handler();

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
                playerShip.launchLaser(false);
            }
        };
    }

    public void init(PlayerData playerData, Level level) {
        this.playerData = playerData;
        this.level = level;
        factory = new GameComponentFactory(screenWidth, screenHeight, getResources(), playerData, level);
        thread = new Thread(this);
        initLevelRes();
        initPlayerRes();
        initEnemyRes();
    }

    public void startGame() {
        run = true;
        thread.start();
    }

    public void stopGame() {
        run = false;
        handlerShield.removeCallbacks(runnableShield);
        handlerLaser.removeCallbacks(runnableLaser);
    }

    @Override
    public void run() {
        while (run) {

            draw();
            onBackgroundAction();
        }
    }

    private void draw() {
        try {
            canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            canvas.save();
            canvas.scale(scaleX, scaleY);
            canvas.drawBitmap(bitmapLevel, coordX1, coordY1, paint);
            canvas.drawBitmap(bitmapLevel, coordX2, coordY2, paint);
            canvas.restore();

            playerShip.onDraw(canvas);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void initLevelRes() {
        bitmapLevel = BitmapFactory.decodeResource(getResources(), level.getImage());
        backgroundWidth = bitmapLevel.getWidth();
        backgroundHeight = bitmapLevel.getHeight();
        scaleX = screenWidth / backgroundWidth;
        scaleY = screenHeight / backgroundHeight;
    }

    private void initPlayerRes() {
        playerShip = factory.createPlayerShip();
    }

    private void initEnemyRes() {
        for (Enemy enemy : level.getEnemies()) {
            enemyShips.add(factory.createEnemyShip(enemy));
        }
        boss = factory.createEnemyShip(level.getBoss());
    }

    public void setAccelerated(float X, float Y) {
        playerShip.setAccelerated(Y, X);
    }

    public void onBackgroundAction() {
        coordY1 += 1;
        if (coordY1 > backgroundHeight) {
            coordY1 = 0;
        }
        coordY2 = coordY1 - backgroundHeight;
    }

    @Override
    public void shotEnemy() {
        playerShip.shotEnemy(factory, playerData.getPlayer().getBullet());
    }

    @Override
    public void openShield() {
        if (shield) return;
        shield = true;
        playerShip.openShield(true);
        handlerShield.postDelayed(runnableShield, DELAY);
    }

    @Override
    public void launchLaser() {
        if (laser) return;
        laser = true;
        playerShip.launchLaser(true);
        handlerLaser.postDelayed(runnableLaser, DELAY);
    }
}
