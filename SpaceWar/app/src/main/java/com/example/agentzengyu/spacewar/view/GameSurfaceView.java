package com.example.agentzengyu.spacewar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
    private float scaleX = 1, scaleY = 1;
    protected Paint paint;
    protected Canvas canvas;
    private Bitmap bitmapLevel;

    private PlayerData playerData;
    private Level level;

    private PlayerShip playerShip;

    public GameSurfaceView(Context context) {
        super(context);
        holder = this.getHolder();
        holder.addCallback(this);
        paint = new Paint();
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = this.getHolder();
        holder.addCallback(this);
        paint = new Paint();
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        holder = this.getHolder();
        holder.addCallback(this);
        paint = new Paint();
    }

    public void init(PlayerData playerData, Level level) {
        this.playerData = playerData;
        this.level = level;
        thread = new Thread(this);
        initLevelRes();
        initPlayerRes();
        initEnemyRes();
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
        canvas = holder.lockCanvas();
        if (canvas!=null){
            canvas.save();
            canvas.scale(scaleX, scaleY);
            canvas.drawBitmap(bitmapLevel, 0, backgroundHeight, paint);
            canvas.restore();

            playerShip.onDraw(canvas);

            if (canvas!=null){
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

    }
}
