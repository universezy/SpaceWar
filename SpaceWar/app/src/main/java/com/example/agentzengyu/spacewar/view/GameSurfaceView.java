package com.example.agentzengyu.spacewar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.agentzengyu.spacewar.entity.basic.set.PlayerData;
import com.example.agentzengyu.spacewar.entity.basic.single.Level;
import com.example.agentzengyu.spacewar.entity.game.PlayerShip;

/**
 * Created by Agent ZengYu on 2017/7/27.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder;
    private Thread thread;
    private boolean run = true;
    private float screenWidth, screenHeight;
    private float backgroundWidth, backgroundHeight;
    private float coordX1 = 0.0f, coordY1 = 0.0f;
    private float coordX2 = 0.0f, coordY2 = 0.0f;
    private float scaleX = 1, scaleY = 1;
    protected Paint paint;
    protected Canvas canvas;
    private Bitmap bitmapLevel;

    private PlayerData playerData;
    private Level level;

    private PlayerShip playerShip;

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

    private void initDefault(){
        holder = this.getHolder();
        holder.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void init(PlayerData playerData, Level level) {
        this.playerData = playerData;
        this.level = level;
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
        playerShip = new PlayerShip(getResources(), playerData.getPlayer().getImage(), playerData.getPlayer().getCrash());
        playerShip.setParams(playerData.getLife().getValue(), playerData.getDefense().getValue(), playerData.getPower().getValue(), playerData.getVelocity().getValue());
        playerShip.setScreenSize(screenWidth, screenHeight);
    }

    private void initEnemyRes() {

    }

    public void setAccelerated(float X, float Y) {
        playerShip.setAccelerated(Y, X);
    }

    public void onBackgroundAction() {
        coordY1 += 1;
        if (coordY1>backgroundHeight){
            coordY1=0;
        }
        coordY2 = coordY1-backgroundHeight;
    }
}
