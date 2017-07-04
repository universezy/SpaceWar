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

public class PlayerView extends View {
    private int shieldColor = Color.parseColor("#30000099");
    private Paint paintShip, paintShield;
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
    private boolean init = false;

    public PlayerView(Context context) {
        super(context);
        init();
    }

    public PlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        float x = playerX * moveWidth / 1000 - bitmapWidth / 2;
        float y = playerY * moveHeight / 1000;
        canvas.drawBitmap(bitmap, x, y, paintShip);

        if (openShield) {
            paintShield.setColor(shieldColor);
            float cx = playerX * moveWidth / 1000;
            float cy = playerY * moveHeight / 1000 + bitmapHeight / 2;
            canvas.drawCircle(cx, cy, radius, paintShield);
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
        radius = Math.max(bitmapWidth, bitmapHeight);
        paintShip = new Paint();
        paintShield = new Paint();
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
     * 护盾
     *
     * @param open 是否开启
     */
    public void shield(boolean open) {
        openShield = open;
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
