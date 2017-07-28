package com.example.agentzengyu.spacewar.entity.game;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Agent ZengYu on 2017/7/28.
 */

public class PlayerShip extends GameObject {
    private boolean laser = false;
    private boolean shield = false;
    private float acceleratedX = 0.0f, acceleratedY = 0.0f;

    public PlayerShip(Resources resources, int objectResId, int crashResId) {
        super(resources, objectResId, crashResId);
        coordX = screenWidth / 2;
        coordY = screenHeight;
    }

    @Override
    public void setParams(float life, float defense, float power, float velocity) {
        super.setParams(life, defense, power, velocity);
    }

    @Override
    public void setScreenSize(float screenWidth, float screenHeight) {
        super.setScreenSize(screenWidth, screenHeight);
    }

    public void setAccelerated(float X, float Y) {
        acceleratedX = X;
        acceleratedY = Y;
        Log.e("X", "  -- " + X);
        Log.e("Y", "  -- " + Y);
    }

    public void setLaser(boolean laser) {
        this.laser = laser;
    }

    public void setShield(boolean shield) {
        this.shield = shield;
    }

    @Override
    public void onDraw(Canvas canvas) {
        action();
        canvas.drawBitmap(objectBitmap, coordX - objectWidth / 2, coordY, paint);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public boolean crash(GameObject target) {
        return false;
    }

    @Override
    protected void action() {
        coordX += acceleratedX * velocity;
        if (coordX < 0) {
            coordX = 0;
        } else if (coordX > screenWidth) {
            coordX = screenWidth;
        }
        coordY += acceleratedY * velocity;
        if (coordY < 0) {
            coordY = 0;
        } else if (coordY > screenHeight) {
            coordY = screenHeight;
        }
        Log.e("coordX", "  -- " + coordX);
        Log.e("coordX", "  -- " + coordX);
    }
}
