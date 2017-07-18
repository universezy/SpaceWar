package com.example.agentzengyu.spacewar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.agentzengyu.spacewar.R;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 玩家位置视图
 */
public class LocationPlayerView extends View {
    private int colorShield = Color.parseColor("#7700bfff");
    private int colorLaser = Color.parseColor("#ddff0000");
    private Paint paintShip, paintShield, paintLaser;
    private Bitmap bitmap;
    private float screenWidth = 0;
    private float screenHeight = 0;
    private float bitmapWidth = 0;
    private float bitmapHeight = 0;
    private float moveWidth = 0;
    private float moveHeight = 0;
    private float radius = 0;
    private float playerX = 500;
    private float playerY = 1000;
    private boolean openShield = false;
    private boolean launchLaser = false;
    private boolean init = false;
    private float rangeLaser = 0;

    public LocationPlayerView(Context context) {
        super(context);
        init();
    }

    public LocationPlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LocationPlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!init) {
            screenWidth = getWidth();
            screenHeight = getHeight();
            moveWidth = screenWidth;
            moveHeight = screenHeight - bitmapHeight;
            playerY = moveHeight;
            init = true;
        }

        float pixelX = playerX * moveWidth / 1000 - bitmapWidth / 2;
        float pixelY = playerY * moveHeight / 1000;
        canvas.drawBitmap(bitmap, pixelX, pixelY, paintShip);

        if (openShield) {
            float shieldX = playerX * moveWidth / 1000;
            float shieldY = playerY * moveHeight / 1000 + bitmapHeight / 2;
            canvas.drawCircle(shieldX, shieldY, radius, paintShield);
        }

        if (launchLaser) {
            float laserX = playerX * moveWidth / 1000 - rangeLaser / 2;
            float laserY = playerY * moveHeight / 1000;
            canvas.drawRect(laserX, -50, laserX + rangeLaser, laserY, paintLaser);
        }
    }

    /**
     * 初始化
     */
    private void init() {
        int normalId = R.mipmap.ic_launcher_round;
        bitmap = BitmapFactory.decodeResource(getResources(), normalId);
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        radius = Math.max(bitmapWidth, bitmapHeight) * 2 / 3;
        paintShip = new Paint();
        paintShield = new Paint();
        paintShield.setColor(colorShield);
        paintLaser = new Paint();
        paintLaser.setColor(colorLaser);
    }

    /**
     * 设置玩家位置
     *
     * @param X
     * @param Y
     */
    public void setLocation(float X, float Y) {
        this.playerX = X;
        this.playerY = Y;
        invalidate();
    }

    /**
     * 设置护盾
     *
     * @param open 是否开启
     */
    public void setShield(boolean open) {
        openShield = open;
        invalidate();
    }

    /**
     * 初始化激光
     *
     * @param range 范围
     */
    public void initLaser(float range) {
        this.rangeLaser = range * 2;
    }

    /**
     * 设置激光
     *
     * @param open
     */
    public void setLaser(boolean open) {
        launchLaser = open;
        invalidate();
    }

    /**
     * 破坏
     */
    public void destroy() {
        int destroyId = R.mipmap.ic_launcher;
        bitmap = BitmapFactory.decodeResource(getResources(), destroyId);
        invalidate();
    }
}
